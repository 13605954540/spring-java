package com.lp.bridge;

/**
 * 桥接模式
 *
 * <pre>
 *     	定义: 将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度
 *     	适用场景：
 *     	    - 当一个类存在两个独立变化的维度，且这两个维度都需要进行扩展时。
 *          - 当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 *          - 当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 * </pre>
 */
public class Sample {

    public interface VideoFile {

        void todo();
    }

    public class AviFile implements VideoFile {

        @Override
        public void todo() {

        }
    }

    public class MovFile implements VideoFile {

        @Override
        public void todo() {

        }
    }

    public abstract class OpSystem {

        protected VideoFile videoFile;

        public OpSystem(VideoFile vf) {
            this.videoFile = vf;
        }

        public abstract void play(String fileName);
    }

    public class Windows extends OpSystem {

        public Windows(VideoFile vf) {
            super(vf);
        }

        @Override
        public void play(String fileName) {
            videoFile.todo();
        }
    }

    public class Linux extends OpSystem {

        public Linux(VideoFile vf) {
            super(vf);
        }

        @Override
        public void play(String fileName) {
            videoFile.todo();
        }
    }

    public static void main(String[] args) {
        Sample sample = new Sample();
        VideoFile videoFile = sample.new AviFile();
        OpSystem opSystem = sample.new Windows(videoFile);
        opSystem.play("gogo");
    }
}
