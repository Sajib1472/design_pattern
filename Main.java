/*
Sajib Biswas - Roll:2007042.
Bridge design pattern and proxy design pattern are implemented to complete the task.
*/

interface TV{
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int vol);

    int getChannel();

    void setChannel(int channel);
}


class GeneralTV implements TV{
    boolean powerOn=false;
    int volume=20;
    int channel=8;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int vol) {
        volume=vol;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel=channel;
    }
}


class SmartTV implements TV{
    boolean powerOn=false;
    int volume=100;
    int channel=10;

    @Override
    public boolean isEnabled() {
        return powerOn;
    }

    @Override
    public void enable() {
        powerOn=true;
    }

    @Override
    public void disable() {
        powerOn=false;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int vol) {
        volume=vol;
    }

    @Override
    public int getChannel() {
        return channel;
    }

    @Override
    public void setChannel(int channel) {
        this.channel=channel;
    }
    public void Youtube(YoutubeClass yt){
        System.out.println("Youtube for SmartTV.");
        yt.runYoutube();
    }
}


class Remote{
    protected TV tv;

    Remote(){tv=null;}
    Remote(TV tv){
        this.tv=tv;
    }
    public void togglePower() {
        if(tv.isEnabled()){
            tv.disable();
            System.out.println("Power turned off.");
        }
        else {
            tv.enable();
            System.out.println("Power turned on.");
        }
    }

    public void volumeUp() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()+5);
            System.out.println("Increasing volume +2.");
        }else{
            System.out.println("Please Turn on tv first.");
        }
    }

    public void volumeDown() {
        if(tv.isEnabled()){
            tv.setVolume(tv.getVolume()-5);
            System.out.println("Decreasing volume -2.");
        }else{
            System.out.println("Please Turn on tv first.");
        }

    }

    public void channelUp() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()+1);
            System.out.println("Channel +1.");
        }else{
            System.out.println("Please Turn on tv first.");
        }


    }

    public void channelDown() {
        if(tv.isEnabled()){
            tv.setChannel(tv.getVolume()-1);
            System.out.println("Channel -1.");
        }else{
            System.out.println("Please Turn on tv first.");
        }

    }
}

class SmartRemote extends Remote{

    SmartRemote(){

    }
    SmartRemote(TV tv){
        super(tv);
    }
    void showYoutube(YoutubeClass yt){
        ((SmartTV)tv).Youtube(yt);
    }
}

//YoutubeCLass implements Proxy Design method
interface YoutubeClass{
    void runYoutube();
}
class FirstLoadYoutube implements YoutubeClass{

    @Override
    public void runYoutube() {
        System.out.println("Running Youtube.");
    }
}

class proxyLoadYoutube implements YoutubeClass{
    private FirstLoadYoutube firstLoadYoutube;
    @Override
    public void runYoutube() {
        if(firstLoadYoutube==null){
            firstLoadYoutube=new FirstLoadYoutube();
            System.out.println("Starting Youtube.");
        }
        firstLoadYoutube.runYoutube();
    }
}

public class Main {
    public static void main(String[] args) {

        //GeneralTV

        GeneralTV generalTV=new GeneralTV();
        Remote remote= new Remote(generalTV);
        System.out.println("General Tv.");
        remote.togglePower();
        remote.volumeUp();
        remote.channelUp();
        remote.channelDown();
        remote.volumeDown();

        //SmartTV
        System.out.println();
        System.out.println("Smart Tv.");
        SmartTV smartTV=new SmartTV();
        SmartRemote sremote= new SmartRemote(smartTV);
        sremote.togglePower();
        sremote.volumeUp();
        sremote.channelUp();
        sremote.channelDown();
        sremote.volumeDown();

        System.out.println();
        //Youtube Class
        YoutubeClass youtubeClass=new proxyLoadYoutube();

        //Accessing Youtube() using smart-remote.
        sremote.showYoutube(youtubeClass);
        sremote.showYoutube(youtubeClass);
    }

}
