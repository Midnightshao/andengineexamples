package project.androidgame;

import android.content.Intent;
import android.os.Handler;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.Camera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.font.Font;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.ui.activity.BaseGameActivity;

public class MainActivity extends BaseGameActivity {
//    private Camera andCamera;
    private Texture myFontTexture;
    private Font myFont;
//    private static final int CAMERA_WIDTH = 320;
//    private static final int CAMERA_HEIGHT = 480;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
private static final int CAMERA_WIDTH = 320;
    private static final int CAMERA_HEIGHT = 480;
    private Camera andCamera;
    private TextureRegion myTextureRegion;
    private Handler mhandler;
    @Override
    public Engine onLoadEngine() {
        mhandler=new Handler();
        // 构建摄像机
        this.andCamera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        // 构建Engine，全屏显示，手机方向为竖屏，按比例拉伸
        return new Engine(new EngineOptions(true, EngineOptions.ScreenOrientation.PORTRAIT,
                new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT),
                this.andCamera));
    }

    @Override
    public void onLoadResources() {

        this.myFontTexture=new Texture(512,512, TextureOptions.DEFAULT);
        this.myTextureRegion= TextureRegionFactory.createFromAsset(this.myFontTexture,this,"android.png",0,0);
        this.mEngine.getTextureManager().loadTexture(this.myFontTexture);
    }

    @Override
    public Scene onLoadScene() {        // 构建场景，允许的最大Layer数量为1
        // 在Log中显示FPS数
        this.mEngine.registerUpdateHandler(new FPSLogger());

        final Scene scene=new Scene(1);

        final int centerX=(CAMERA_WIDTH-this.myTextureRegion.getWidth())/2;
        final int centerY=(CAMERA_HEIGHT-this.myTextureRegion.getHeight())/2;

        final Sprite sprite=new Sprite(centerX,centerY,this.myTextureRegion);

        scene.getLastChild().attachChild(sprite);
        return scene;
    }

    @Override
    public void onLoadComplete() {
        mhandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,MainMenuActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
