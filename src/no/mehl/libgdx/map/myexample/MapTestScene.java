package no.mehl.libgdx.map.myexample;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import no.mehl.libgdx.map.info.MapManager;
import no.mehl.libgdx.map.info.tilefactory.MapQuestTileFactoryInfo;
import no.mehl.libgdx.map.ui.MapListener;
import no.mehl.libgdx.map.ui.MapWidget;

public class MapTestScene implements Screen {

    private Stage stage;
    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch SBatch;

    private int Width;
    private int Height;
    private MapListener mapListener;

    @Override
    public void render(float delta) {

        Gdx.gl.glViewport(0, 0, Width, Height);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SBatch.setProjectionMatrix(camera.combined);



        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {
        Width=Gdx.graphics.getWidth();
        Height=Gdx.graphics.getHeight();


        camera = new OrthographicCamera();
        camera.setToOrtho(false,Width , Height);
        camera.update();
        SBatch = new SpriteBatch();

        SBatch.setProjectionMatrix(camera.combined);
        viewport = new ExtendViewport(Height, Width ,camera);
        stage = new Stage(viewport, SBatch);




        MapManager manager = new MapManager(new MapQuestTileFactoryInfo());

        manager.update();
        MapWidget widget = new MapWidget(manager);
        widget.setSize(stage.getWidth(), stage.getHeight());
        stage.addActor(widget);


       /* final Texture t = new Texture(Gdx.files.internal("assets/add.png"));
        Image image = new Image(t);
        image.setWidth(300);
        image.setHeight(300);
        image.setPosition(100, 100);
       stage.addActor(image);*/

        mapListener = new MapListener(manager);

       stage.addListener(mapListener);

       CreateInputProcessor();

    }




    private void CreateInputProcessor() {
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
