public class Playlist{
    
    String name;
    int index = 0;
    Song [] songs = new Song [10];
   
    public Playlist(String name){
        this.name = name;
        System.out.println(name + " created.");
    }
   
    void addSong(Song s){
        songs[index] = s;
        index++;
        System.out.println(s.title + " added to " + name + ".");

    }
    void addSong(Song s, int d){
        if(d<0 || d>index){
            System.out.println("Invalid index.");
        }else{
            for(int i=index; i>d; i--){
                songs[i] = songs[i - 1];
            }
        }
        
            songs[d]=s;
            index++;
            System.out.println(s.title + " added to " + name);
    }
    void deleteSong(String name){
        int find = -1;
        for(int i=0; i<index-1; i++){
            if(songs[i].title.equals(name)){
                find = i; 
            }
            
        }
        if(find ==0){
                System.out.println(name + " not found in " + this.name + ".");
            }else{
                for(int i=find; i<index; i--){
                    songs[i] = songs[i+1];
                }
            }
            index--;
            songs[index] = null;
    }
    
    void info(){
       System.out.println(name + " has the following songs: ");
      for(int i=0; i<index; i++){
        System.out.println(songs[i].title);
        System.out.println(songs[i].artist);
        System.out.println(songs[i].length);
      }
    }
}