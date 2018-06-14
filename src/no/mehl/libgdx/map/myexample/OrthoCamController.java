package no.mehl.libgdx.map.myexample;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;


public class OrthoCamController extends InputAdapter {
	final OrthographicCamera camera;
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();

	public OrthoCamController(OrthographicCamera camera) {
		this.camera = camera;

	}


	
	
	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		camera.unproject(curr.set(x, y, 0));
		if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
			camera.unproject(delta.set(last.x, last.y, 0));
			delta.sub(curr);
			Vector3 pos=new Vector3(camera.position);
			
			pos.add(delta.x,delta.y,0);
			Rectangle intersection=new Rectangle();
			Rectangle loc=new Rectangle(x,y,x,y);
			boolean canMove= Intersector.intersectRectangles(new Rectangle(pos.x-camera.viewportWidth/2,pos.y-camera.viewportHeight/2,camera.viewportWidth-1,camera.viewportHeight-1),
					loc,
					intersection);
			if(!canMove){
				return false;}
			else{
			camera.position.add(delta.x, delta.y, 0);}
		}
		last.set(x, y, 0);
		return false;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return false;
	}




	public boolean touchDown (int screenX, int screenY, int pointer, int button) {
		
		return false;
	}
	
}
