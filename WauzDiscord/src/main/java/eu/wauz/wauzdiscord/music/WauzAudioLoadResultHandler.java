package eu.wauz.wauzdiscord.music;

import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException.Severity;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import eu.wauz.wauzdiscord.ShiroDiscordBot;
import eu.wauz.wauzdiscord.ShiroDiscordBotConfiguration;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * A subclass of the bot to handle loading attempts of audio.
 * 
 * @author Wauzmons
 */
public class WauzAudioLoadResultHandler implements AudioLoadResultHandler {
	
	/**
	 * The Discord bot running on the server.
	 */
	private ShiroDiscordBot shiroDiscordBot;
	
	/**
	 * The channel to write the response.
	 */
	private MessageChannel messageChannel;
	
	/**
	 * If the bot should instantly skip to this song.
	 */
	private boolean startPlaying;
	
	/**
	 * The player created from the player manager that provides sound.
	 */
	private AudioPlayer audioPlayer;
	
	/**
	 * The list of all queued audio tracks.
	 */
	private List<AudioTrack> audioQueue;
	
	/**
	 * Creates a new audio load result handler for the given bot.
	 * 
	 * @param shiroDiscordBot The Discord bot running on the server.
	 * @param messageChannel The channel to write the response.
	 * @param startPlaying If the bot should instantly skip to this song.
	 */
	public WauzAudioLoadResultHandler(ShiroDiscordBot shiroDiscordBot, MessageChannel messageChannel, boolean startPlaying) {
		this.shiroDiscordBot = shiroDiscordBot;
		this.messageChannel = messageChannel;
		this.startPlaying = startPlaying;
		
		audioPlayer = shiroDiscordBot.getAudioPlayer();
		audioQueue = shiroDiscordBot.getAudioQueue();
	}
	
	/**
	 * If a single track was loaded, it gets added to the audio queue.
	 */
	@Override
	public void trackLoaded(AudioTrack audioTrack) {
		if(startPlaying || audioQueue.isEmpty()) {
			shiroDiscordBot.joinAudioChannel();
			audioQueue.add(audioTrack);
			audioPlayer.playTrack(audioTrack);
			messageChannel.sendMessage("Shiro will now play your track! Nya~!").queue();
		}
		else {
			audioQueue.add(audioTrack);
			messageChannel.sendMessage("Shiro added your track to the queue! Nya~!").queue();
		}
	}
	
	/**
	 * If a playlist was loaded, its tracks get added to the audio queue.
	 */
	@Override
	public void playlistLoaded(AudioPlaylist audioPlaylist) {
		boolean audioQueueEmpty = audioQueue.isEmpty();
		audioQueue.addAll(audioPlaylist.getTracks());
		messageChannel.sendMessage("Shiro added your playlist to the queue! Nya~!").queue();
		if(startPlaying || audioQueueEmpty) {
			shiroDiscordBot.joinAudioChannel();
			audioPlayer.playTrack(audioQueue.get(0));
		}
	}
	
	/**
	 * Print an error if the source is unreadable.
	 */
	@Override
	public void noMatches() {
		messageChannel.sendMessage("Sorry! Shiro can't read this audio source!").queue();
	}
	
	/**
	 * Print an error if the loading of the track caused an exception.
	 */
	@Override
	public void loadFailed(FriendlyException exception) {
		if(exception.severity.equals(Severity.COMMON)) {
			messageChannel.sendMessage("Shiro got this error: " + exception.getMessage()).queue();
		}
		else {
			messageChannel.sendMessage(ShiroDiscordBotConfiguration.ERROR_MESSAGE).queue();
		}
	}

}
