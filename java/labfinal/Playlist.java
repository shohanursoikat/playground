import java.util.ArrayList;
public class Playlist{
    public String name;
    public Playlist(String n){
        name=n;
        System.out.println(name+" created.");
    }
    
    ArrayList<Song> songs = new ArrayList<>();
    

    public void addSong(Song s){
        if(s==null){
            System.out.println("Unable to add song.");
            return;
        }
        
        songs.add(s);
       
        System.out.println(s.title+" added to playlist.");
        
    }
     public void addSong(Song song, int index){
        if(song==null){
            System.out.println("Unable to add song.");
            return;
        }
        songs.add(index,song);
       
        System.out.println(song.title+" added to playlist.");
         
    }
    public void deleteSong(String ti){
        boolean found= false;
        for(int i=0; i<songs.size(); i++){
            if(songs.get(i).title.equals(ti)){
                songs.remove(i);
                System.out.println( ti+" removed from the list.");
                found=true;
                break;
            }
        }
        if(found==false){
            System.out.println("Song not found.");
        }
    }
    public void info(){
        System.out.println("First playlist has the following songs: ");
        
       
        if(songs.isEmpty()){
            System.out.println("First playlist has no songs.");
        }
            for(Song ss : songs){ 
            
            System.out.println("Title: "+ss.title);
            System.out.println("Artist: "+ss.artist);
            System.out.println("Length: "+ss.length);
        }
    }
         
       }
    
