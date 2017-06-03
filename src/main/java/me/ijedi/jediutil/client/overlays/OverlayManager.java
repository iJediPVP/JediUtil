package me.ijedi.jediutil.client.overlays;

import me.ijedi.jediutil.exceptions.OverlayNotFoundException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OverlayManager {

    // Create the overlays objects
    private static BiomeOverlay biomeOverlay;
    private static ClockOverlay clockOverlay;
    private static CoordinateOverlay coordinateOverlay;
    private static DirectionOverlay directionOverlay;
    private static FrameRateOverlay frameRateOverlay;

    private static List<AbstractOverlay> overlayList;

    public static int firstYPosition = 5;
    private int intervalYPosition = 10;

    public static final String OVERLAY_CATEGORY = "overlay";

    // Constructor
    public OverlayManager(){

        biomeOverlay = new BiomeOverlay();
        clockOverlay = new ClockOverlay();
        coordinateOverlay = new CoordinateOverlay();
        directionOverlay = new DirectionOverlay();
        frameRateOverlay = new FrameRateOverlay();

        // Temporary
        coordinateOverlay.setOverlayRank(0);
        directionOverlay.setOverlayRank(1);
        clockOverlay.setOverlayRank(2);
        frameRateOverlay.setOverlayRank(3);
        biomeOverlay.setOverlayRank(4);

        // Index determines button id and button order
        overlayList = new ArrayList<AbstractOverlay>(){{
            add(0,biomeOverlay);
            add(1,clockOverlay);
            add(2,coordinateOverlay);
            add(3,directionOverlay);
            add(4,frameRateOverlay);
        }};
    }


    public List<AbstractOverlay> getOverlayList(){
        if(overlayList.equals(null)){
            overlayList = new ArrayList<>();
        }
        return overlayList;
    }

    public List<AbstractOverlay> getEnabledOverlayList(boolean sort){
        List<AbstractOverlay> enabledOverlays = new ArrayList<>();
        for(AbstractOverlay overlay : getOverlayList()){
            if(overlay.getOverlayEnum().isEnabled()){
                enabledOverlays.add(overlay);
            }
        }

        if(sort){
            Collections.sort(enabledOverlays, new Comparator<AbstractOverlay>() {
                @Override
                public int compare(AbstractOverlay o1, AbstractOverlay o2) {
                    return Integer.toString(o1.getOverlayRank()).compareTo(Integer.toString(o2.getOverlayRank()));
                }
            });
        }

        return enabledOverlays;
    }

    // Other public methods
    public void setOverlayPositions(){
        List<AbstractOverlay> enabledOverlays = getEnabledOverlayList(true);
        for(int x = 0; x < enabledOverlays.size(); x++){
            AbstractOverlay overlay = enabledOverlays.get(x);
            int yPosition = (intervalYPosition * x) + firstYPosition;
            overlay.setPositionY(yPosition);
        }
    }

    public AbstractOverlay getOverlayFromMenuButtonId(int buttonId){
        if(!overlayList.equals(null)){

            for(AbstractOverlay overlay : overlayList){
                if(overlay.getMenuButton().id == buttonId){
                    return overlay;
                }
            }
        }

        throw new OverlayNotFoundException("Could not find overlay from button id: " + Integer.toString(buttonId));
    }



}
