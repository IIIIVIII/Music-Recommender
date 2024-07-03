import java.io.*;
import java.util.ArrayList;
/**
 * Exception to be thrown when files provided for MusicRecommender is malformed.
 *
 * @version 2022-07-25
 * @author Purdue CS
 */
public class MusicRecommender {
    private ArrayList<Music> music = new ArrayList<>();
    private String musicListFileName;

    public MusicRecommender(String musicListFileName) throws FileNotFoundException, MusicFileFormatException {
        try {
            this.musicListFileName = musicListFileName;
            File file = new File(this.musicListFileName);
            FileReader fr = new FileReader(file);
            BufferedReader bfr = new BufferedReader(fr);
            String musicInfoLine = bfr.readLine();
            while (musicInfoLine != null) {
                Music m = MusicRecommender.parseMusic(musicInfoLine);
                music.add(m);
                musicInfoLine = bfr.readLine();
            }
            bfr.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException();
        } catch (MusicFileFormatException e) {
            throw new MusicFileFormatException("One of the lines of the music list file is malformed!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Music parseMusic(String musicInfoLine)
            throws MusicFileFormatException {
        // Blank_Space Taylor_Swift Electro_Pop 96 143

        String[] arr = musicInfoLine.split(" ");
        if (arr.length !=5) {
            throw new MusicFileFormatException("One of the lines of the music list file is malformed!");
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i].replace("_", " ");
        }
        // throw a MusicFileFormatException
        Music str = new Music(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]));
        musicInfoLine.replaceAll("_", " "); // Blank Space Taylor Swift Electro Pop 96 143
        return str;



    }

    public ArrayList<Music> searchArtists(MusicProfile musicProfile)
            throws NoRecommendationException {

        ArrayList<Music> artistsArrayList = new ArrayList<>();
        for (int i = 0; i < music.size(); i++) {
            if (music.get(i).getArtist().toLowerCase().compareTo(musicProfile.getPreferredArtist()) == 0) {
                artistsArrayList.add(music.get(i));
            }
        }
        Music isNull = new Music("", "", "", 0, 0);
        if (artistsArrayList.isEmpty()) {
            throw new NoRecommendationException("No music by your preferred artist is in the list!");
        }
        return artistsArrayList;
    }

    public Music BPMBasedRecommendation(MusicProfile musicProfile)
            throws NoRecommendationException {
        ArrayList<Music> bPMArrayList = new ArrayList<>();
        ArrayList<Music> bpmArrayList = new ArrayList<>();
        Music isNull = new Music("", "", "", 0, 0);
        int max = 9999;
        Music isNull2 = null;
        for (int i = 0; i < music.size(); i++) {
            if (music.get(i).getBPM() == musicProfile.getPreferredBPM()) {
                bPMArrayList.add(music.get(i));
            } else {
                if (Math.abs(music.get(i).getBPM() - musicProfile.getPreferredBPM()) < max) {
                    max = Math.abs(music.get(i).getBPM() - musicProfile.getPreferredBPM());
                    isNull = music.get(i);
                }
            }
        }
        if (isNull != null) {
            int bpm = isNull.getBPM();
            for (int i = 0; i < music.size(); i++) {
                if (music.get(i).getBPM() == bpm) {
                    bpmArrayList.add(music.get(i));
                }
            }
            if (bpmArrayList.size() > 1) {
                if (!musicProfile.isLikePopular()) {
                    int min = bpmArrayList.get(0).getPopularity();
                    for (Music k : bpmArrayList) {
                        if (k.getPopularity() < min) {
                            min = k.getPopularity();
                            isNull2 = k;
                        }
                    }
                    return isNull2;
                } else {
                    int max2 = bpmArrayList.get(0).getPopularity();
                    for (Music j : bpmArrayList) {
                        if (j.getPopularity() > max2) {
                            max2 = j.getPopularity();
                            isNull2 = j;
                        }
                        return isNull2;
                    }
                }
            }
            return bpmArrayList.get(0);
        }
        if (bPMArrayList.size() > 1) {
            if (!musicProfile.isLikePopular()) {
                int min = bpmArrayList.get(0).getPopularity();
                for (Music k : bpmArrayList) {
                    if (k.getPopularity() < min) {
                        min = k.getPopularity();
                        isNull2 = k;
                    }
                }
                return isNull2;
            }
        } else {
            int max2 = bpmArrayList.get(0).getPopularity();
            for (Music j : bpmArrayList) {
                if (j.getPopularity() > max2) {
                    max2 = j.getPopularity();
                    isNull2 = j;
                }
                return isNull2;
            }
        }
        if (max > 20) {
            throw new NoRecommendationException("There was no music with your preferred BPM!");
        }

        if (bPMArrayList.size() == 1) {
            return bPMArrayList.get(0);
        }
        return null;
    }


    public Music genreBasedRecommendation(MusicProfile musicProfile)
            throws NoRecommendationException {
        Music isGenre = new Music("", "", musicProfile.getPreferredGenre(), 0, 0);
        if (!musicProfile.isLikePopular()) {
            for (int i = 0; i < this.music.size(); i++) {
                if (music.get(i).getGenre().toLowerCase().compareTo(musicProfile.getPreferredGenre()) == 0) {
                    if (isGenre.getGenre().toLowerCase().compareTo(musicProfile.getPreferredGenre()) != 0) {
                        isGenre = music.get(i);
                    } else if (music.get(i).getPopularity() < isGenre.getPopularity()) {
                        isGenre = music.get(i);
                    }
                }
            }
        } else {
            for (int i = 0; i < music.size(); i++) {
                if (music.get(i).getGenre().toLowerCase().compareTo(musicProfile.getPreferredGenre()) == 0) {
                    if (isGenre.getGenre().toLowerCase().compareTo(musicProfile.getPreferredGenre()) != 0) {
                        isGenre = music.get(i);
                    } else if (music.get(i).getPopularity() > isGenre.getPopularity()) {
                        isGenre = music.get(i);
                    }
                }
            }
        }
        if (isGenre.getArtist() == null) {
            throw new NoRecommendationException("There was no music with your preferred genre!");
        }
        return isGenre;
    }

    public Music getMostPopularMusic() {
        int count = 0;
        Music music1 = null;
        for (Music i : music) {
            if (count < i.getPopularity()) {
                count = i.getPopularity();
                music1 = i;
            }
        }
        return music1;
    }

    public void saveMusicList() {
        try {
            String[] normal = new String[music.size()];
            for (int i = 0; i < this.music.size(); i++) {
                normal[i] = String.valueOf(music.get(i));
                normal[i].replace("_", " ");
                String str = normal.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

