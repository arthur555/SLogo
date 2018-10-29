package view.utils;

import javafx.animation.Animation;
import javafx.animation.PathTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.ArrayDeque;
import java.util.Queue;

public class AnimationQueue {
    private ArrayDeque<Animation> queue;
    private int limit;
    private SimpleBooleanProperty playing;

    public AnimationQueue(int limit){
        queue = new ArrayDeque<>();
        this.limit = limit;
        playing = new SimpleBooleanProperty(false);
        playing.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue==false && !queue.isEmpty())
                {
                    playNew();
                }
            }
        });
    }

    public void playNew( ){
        Animation toPlay = queue.pop();
        playing.set(true);
        toPlay.play();
    }

    public SimpleBooleanProperty getPlaying(){
        return playing;
    }


    public Animation makeAnimation(ImageView turtle, Path path, Double newAngle, DoubleProperty duration, double oldAngle){
        System.out.println(path);
        System.out.println(newAngle);
        if (queue.size()>= limit)
            throw new StackOverflowError("The animation Queue is overloaded");
        var pt = new PathTransition(Duration.millis(duration.doubleValue()),path,turtle);
        var rt = new RotateTransition(Duration.millis(duration.doubleValue()/2), turtle);
        rt.setToAngle(newAngle);
        pt.setDuration(Duration.millis(duration.doubleValue()));
        rt.setDuration(Duration.millis(duration.doubleValue()));

        Animation toAdd;

        if(newAngle == oldAngle) {
            toAdd = new SequentialTransition(turtle,pt);
        } else {
            toAdd = new SequentialTransition(turtle,rt);
        }
        queue.add(toAdd);
        if(playing.get() == false)
            playNew();
        System.out.println(toAdd);
        return toAdd;
    }






}
