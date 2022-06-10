package edu.design.pattern;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
 
// Iterator pattern provides you uniform way of accessing different collections of objects
// If you get array, arraylist and hashtable of objects, you can use iterator to uniformly access them
// You can also write polymorphic code because you can refer to each collection of objects because they implement the same interface
public class IteratorPattern {

	public static void main(String[] args) {
		SongsOfThe70s songs70s = new SongsOfThe70s();
		SongsOfThe80s songs80s = new SongsOfThe80s();
		DiscJockey2 madMike =  new DiscJockey2(songs70s, songs80s);
		madMike.showTheSongs();
	}
}
class SongInfo{
	private String songName;
	private String bandName;
	private int releaseYear;
	public SongInfo(String songName, String bandName, int releaseYear) {
		this.songName = songName;
		this.bandName = bandName;
		this.releaseYear = releaseYear;
	}
	public String getSongName() {
		return songName;
	}
	public String getBandName() {
		return bandName;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
}
interface SongIterator{
	public Iterator createIterator();
}
class SongsOfThe70s implements SongIterator{
	ArrayList<SongInfo> bestSongs;
	public SongsOfThe70s(){
		bestSongs = new ArrayList<SongInfo>();
		addSong("song1","desc1",1970);
		addSong("song2","desc2",1970);
		addSong("song3","desc3",1970);
	}
	public void addSong(String songName, String bandName, int releaseYear) {
		SongInfo SongInfo = new SongInfo(songName, bandName, releaseYear);
		bestSongs.add(SongInfo);
	}
	public Iterator createIterator() {
		return bestSongs.iterator();
	}
}
class SongsOfThe80s implements SongIterator{
	SongInfo[] bestSongs;
	int arrayIndex = 0;
	public SongsOfThe80s(){
		bestSongs = new SongInfo[3];
		addSong("song1","desc1",1970);
		addSong("song2","desc2",1970);
		addSong("song3","desc3",1970);
	}
	public void addSong(String songName, String bandName, int releaseYear) {
		SongInfo SongInfo = new SongInfo(songName, bandName, releaseYear);
		bestSongs[arrayIndex++] = SongInfo;
	}
	public Iterator createIterator() {
		return Arrays.asList(bestSongs).iterator();
	}
}
class DiscJockey2{
	SongIterator songsOfThe70s;
	SongIterator songsOfThe80s;
	public DiscJockey2(SongIterator the70sItr, SongIterator the80sItr) {
		this.songsOfThe70s = the70sItr;
		this.songsOfThe80s = the80sItr;
	}
	public void showTheSongs(){
		Iterator songs70s = songsOfThe70s.createIterator();
		Iterator songs80s = songsOfThe80s.createIterator();
		System.out.println("70s songs: ");
		printTheSongs(songs70s);
		System.out.println("80s songs: ");
		printTheSongs(songs80s);
	}
	private void printTheSongs(Iterator iterator) {
		while(iterator.hasNext()){
			SongInfo songsInfo = (SongInfo)iterator.next();
			System.out.println(songsInfo.getSongName());
			System.out.println(songsInfo.getBandName());
			System.out.println(songsInfo.getReleaseYear());
		}
	}
}
