package edu.design.pattern;

import java.util.ArrayList;
import java.util.Iterator;
 
// Allows you treat individual objects and compositions of objects uniformly
// They allows you to represent part-hirarchies. Components can be further divided in to smaller components.
// You can structure data, or represent the inner working of every part of a whole object individually
// A composite can contain never ending groups
public class CompositePattern {

	public static void main(String[] args) {
		SongComponent everySong = new SongGroup("Song List", "Desc");
		SongComponent songGroup1 = new SongGroup("Song Group1", "Desc1");
		SongComponent songGroup2 = new SongGroup("Song Group2", "Desc1");
		SongComponent songGroup3 = new SongGroup("Song Group3", "Desc1");
		
		everySong.add(songGroup1);
		songGroup1.add(new Song("Song1","Band1",2001));
		songGroup1.add(new Song("Song2","Band2",2002));
		songGroup1.add(songGroup2);
		songGroup2.add(new Song("Song3","Band3",2001));
		songGroup2.add(new Song("Song4","Band4",2001));
		everySong.add(songGroup3);
		songGroup3.add(new Song("Song5","Band5",2001));
		songGroup3.add(new Song("Song6","Band6",2001));
		
		DiscJockey crazyList = new DiscJockey(everySong);
		crazyList.getSongList();
	}
}
abstract class SongComponent{
	public void add(SongComponent songComponent){
		throw new UnsupportedOperationException();
	}
	public void remove(SongComponent songComponent){
		throw new UnsupportedOperationException();
	}
	public SongComponent getComponent(int songComponentIndex){
		throw new UnsupportedOperationException();
	}
	public String bandName(){
		throw new UnsupportedOperationException();
	}
	public int releaseYear(){
		throw new UnsupportedOperationException();
	}
	public void displaySognInfo(){
		throw new UnsupportedOperationException();
	}
}
class SongGroup extends SongComponent{
	ArrayList<SongComponent> songComponents = new ArrayList<SongComponent>();
	private String groupName;
	private String groupDescription;

	public SongGroup(String groupName, String groupDescription) {
		this.groupName = groupName;
		this.groupDescription = groupDescription;
	}
	public String getGroupName(){
		return groupName;
	}
	public String getGgroupDescription(){
		return groupDescription;
	}
	public void add(SongComponent songComponent){
		songComponents.add(songComponent);
	}
	public void remove(SongComponent songComponent){
		songComponents.remove(songComponent);
	}
	public SongComponent getComponent(int songComponentIndex){
		return songComponents.get(songComponentIndex);
	}
	public void displaySognInfo(){
		System.out.println(getGroupName()+" "+getGgroupDescription());
		Iterator interator = songComponents.iterator();
		while(interator.hasNext()){
			SongComponent songInfo = (SongComponent) interator.next();
			songInfo.displaySognInfo();
		}
	}
}
class Song extends SongComponent{
	private String songName;
	private String bandName;
	private int releaseYear;
	
	public Song(String songName, String bandName, int releaseYear) {
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
	public void displaySognInfo(){
		System.out.println(getSongName()+" was recorded by "+getBandName()+" in "+getReleaseYear());
	}
}
class DiscJockey{
	SongComponent songList;
	public DiscJockey(SongComponent songList){
		this.songList = songList;
	}
	public void getSongList(){
		songList.displaySognInfo();
	}
}
