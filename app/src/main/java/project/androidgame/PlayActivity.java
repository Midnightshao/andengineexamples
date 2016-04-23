package project.androidgame;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.handler.IUpdateHandler;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.modifier.MoveModifier;
import org.anddev.andengine.entity.primitive.Rectangle;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.text.ChangeableText;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.input.touch.TouchEvent;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.BuildableTexture;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;
import org.anddev.andengine.util.modifier.ease.EaseLinear;

import java.util.Random;

public class PlayActivity extends BaseGameActivity implements View.OnTouchListener{
    private static final int CAMERA_WIDTH = 1200;
    private static final int CAMERA_HEIGHT = 1920;

    private volatile boolean aBoolean=true;
    private volatile boolean aBooleanRight=true;

    private volatile boolean aBooleanTop=true;
    private volatile boolean aBooleanBottom=true;

    private Camera andCamera;
    private TextureRegion myTextureRegion;
    private TextureRegion mTextureRegion2;
    private TextureRegion myTextureRegion1;
    TiledTextureRegion tiledTextureRegion;
    Point point;
    public static Sprite sprite_my;
    private Handler mhandler;
    private Font myFont;
    private ChangeableText text;
    private int scrol=0;
    @Override
    public Engine onLoadEngine() {

        point=new Point();
        mhandler= new Handler();

        this.andCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        // 构建Engine，全屏显示，手机方向为竖屏，按比例拉伸
        return new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                this.andCamera));
    }
    Handler handlers=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(PlayActivity.this,"碰撞",Toast.LENGTH_SHORT).show();
        }
    };
    private  int centerX;
    private  int centerY;
    Random random;
    Random random1;
    Random random2;
    Random random3;
    @Override
    public void onLoadResources() {
        random=new Random();
        random1=new Random();
        random2=new Random();
        random3=new Random();

        Texture myTexture = new Texture(256, 256, TextureOptions.DEFAULT);
        // 加载指定路径纹理到myTextureRegion
        this.myTextureRegion = TextureRegionFactory.createFromAsset(myTexture,
                this, "ic_launcher.png", 0, 0);

        BuildableTexture myTexture1 = new BuildableTexture(256, 256, TextureOptions.DEFAULT);

        this.myTextureRegion1=TextureRegionFactory.createFromAsset(myTexture, this, "ic_launcher.png", 0, 0);


        this.tiledTextureRegion=TextureRegionFactory.createTiledFromAsset(myTexture1, this, "ic_launcher.png", 10, 10);

        this.myTextureRegion1=TextureRegionFactory.createFromAsset(myTexture, this, "ic_launcher.png", 0, 0);

        centerX = (CAMERA_WIDTH - this.myTextureRegion.getWidth()) / 2;

        centerY = (CAMERA_HEIGHT - this.myTextureRegion.getHeight()) / 2;

        Texture myTexture_font = new Texture(256, 256, TextureOptions.DEFAULT);

        this.myFont = new Font(myTexture_font, Typeface.create(
                Typeface.DEFAULT_BOLD, Typeface.NORMAL), 60, true, Color.BLUE);

        this.getEngine().getTextureManager().loadTexture(myTexture);
        this.getEngine().getTextureManager().loadTexture(myTexture_font);
        this.getEngine().getFontManager().loadFont(this.myFont);
    }

    public Scene scene;
    public volatile boolean que=false;

    @Override
    public Scene onLoadScene() {

        FPSLogger fpsLogger=new FPSLogger();

        getEngine().registerUpdateHandler(fpsLogger);

        scene=new Scene(1);

        ColorBackground colorBackground=new ColorBackground(240,240,240);

        scene.setBackground(colorBackground);

        text = new ChangeableText(30, 30, this.myFont,
                "0", 32);

        sprite_my = new Sprite(centerX, centerY, this.myTextureRegion);

        scene.setOnSceneTouchListener(new Scene.IOnSceneTouchListener() {
            @Override
            public boolean onSceneTouchEvent(Scene scene, TouchEvent touchEvent) {
                switch (touchEvent.getAction()) {

                    case TouchEvent.ACTION_MOVE:
                        int po = Math.abs(point.getY() - (int) touchEvent.getY());
                        if (po > 0) {
                            sprite_my.setPosition((int) touchEvent.getX(), (int) touchEvent.getY()-350);
                        }
                        aBoolean = true;
                        aBooleanRight = true;

                        point.setX((int) touchEvent.getX());
                        point.setY((int) touchEvent.getY());
                        break;
                    case TouchEvent.ACTION_DOWN:
//                        point.setX((int) touchEvent.getX());
//                        point.setY((int) touchEvent.getY());
//                        if (touchEvent.getX() < centerX) {
//                            aBoolean = false;
//                            aBooleanTop = true;
//                            aBooleanBottom = true;
//                            Log.i("String", "左边");
//                        } else if (touchEvent.getX() > centerX) {
//                            aBooleanRight = false;
//                            aBooleanTop = true;
//                            aBooleanBottom = true;
//                            Log.i("String", "右边");
//                        }
                        break;
                    case TouchEvent.ACTION_UP:
                        aBoolean = true;
                        aBooleanRight = true;
                        aBooleanTop = true;
                        aBooleanBottom = true;
                        break;
                }
                return true;
            }
        });
        final int i=0;
        final Rectangle rectangle;

        final Handler handlerq=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                final Rectangle rectangle;
                switch (msg.what){
                    case 0:
                        sprite_my.setPosition(sprite_my.getX() - 10, sprite_my.getY());
                        break;
                    case 1:
                        sprite_my.setPosition(sprite_my.getX() + 10, sprite_my.getY());
                        break;
                    case 2:
                        sprite_my.setPosition(sprite_my.getX(), sprite_my.getY() + 10);
                        break;
                    case 3:
                        sprite_my.setPosition(sprite_my.getX(), sprite_my.getY() - 10);
                        break;
                    case 4:
                        int result= 1+random.nextInt(10);
                        int result_color=250;
                        int result_color1=250;
                        int result_color2=250;

                        rectangle= new Rectangle(CAMERA_WIDTH / result - myTextureRegion.getWidth() / 2,0-2*myTextureRegion.getHeight(),400,200);

                        rectangle.setColor(0, 0, 0);

//                        final Sprite sprite1 = new Sprite(CAMERA_WIDTH / result - myTextureRegion.getWidth() / 2, 0, PlayActivity.this.myTextureRegion.clone());
//                        final Sprite sprite1 = new Sprite(CAMERA_WIDTH / result - myTextureRegion.getWidth() / 2, 0, rectangle);
                        rectangle.registerEntityModifier(
                                new MoveModifier(9,
                                        CAMERA_WIDTH / result - myTextureRegion.getWidth() / 2,
                                        CAMERA_WIDTH / result - myTextureRegion.getWidth() / 2, 0-2*myTextureRegion.getHeight(),
                                        CAMERA_HEIGHT + myTextureRegion.getHeight() / 2,
                                        EaseLinear.getInstance()));
                        scene.attachChild(rectangle);

                        getEngine().registerUpdateHandler(new IUpdateHandler() {
                            @Override
                            public void onUpdate(float v) {
                                if (sprite_my.collidesWith(rectangle)) {

                                    que = true;
                                    Log.i("Tag", "yes");

                                }
                            }

                            @Override
                            public void reset() {

                            }
                        });
//                        scene.attachChild(sprite1);
                        if(que){

                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            scrol++;
                                            text.setText(String.valueOf(scrol));
                                            Toast.makeText(PlayActivity.this, "碰撞", Toast.LENGTH_SHORT).show();
                                            scene.attachChild(text);
                                        }
                                    });

                                }
                            }).start();
                            que=false;
                        }
                        break;
                    case 5:


                        break;

                }
            }
        };



        //引擎部分，动力源
        getEngine().registerUpdateHandler(new IUpdateHandler() {
            @Override
            public void onUpdate(float v) {
//                if (!aBoolean) {
//                    sprite_my.setPosition(sprite_my.getX() - 10, sprite_my.getY());
//                }
//                if (!aBooleanRight) {
//                    sprite_my.setPosition(sprite_my.getX() + 10, sprite_my.getY());
//                }
//                if (!aBooleanTop) {
//                    handlerq.sendEmptyMessage(2);
//                }
//                if (!aBooleanBottom) {
//                    handlerq.sendEmptyMessage(3);
//                }

            }

            @Override
            public void reset() {
                Log.i("Tag", "hahah2222222233333");
            }
        });
        scene.attachChild(text);
        scene.attachChild(sprite_my);


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    handlerq.sendEmptyMessage(4);
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        return scene;

    }




    class Point{
        private  int x;
        private  int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
    @Override
    public void onLoadComplete() {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return false;
    }
}
