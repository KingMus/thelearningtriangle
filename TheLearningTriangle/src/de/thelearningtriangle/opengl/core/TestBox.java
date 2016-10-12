package de.thelearningtriangle.opengl.core;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class TestBox
{
	private float position_y;
	private float position_x;
	private float position_depth;
	
	private float height;
	private float width;
	
	private List<KeyEventAction> keyEventActions;
	
	public TestBox(float height, float width)
	{
		this.height = height;
		this.width = width;
		
		this.position_x = 0.0f;
		this.position_y = 0.0f;
		
		initializeKeyEvents();
	}
	
	private final int[] directionSpeeds = new int[2];
	
	private void initializeKeyEvents()
	{
		this.keyEventActions = new ArrayList<KeyEventAction>();
		KeyEventAction keyEvent_Up = createMoveKeyEventAction(KeyEvent.VK_W, Direction.VERTICAL, 1);
		KeyEventAction keyEvent_Down = createMoveKeyEventAction(KeyEvent.VK_S, Direction.VERTICAL, -1);
		KeyEventAction keyEvent_Left = createMoveKeyEventAction(KeyEvent.VK_A, Direction.HORIZONTAL, -1);
		KeyEventAction keyEvent_Right = createMoveKeyEventAction(KeyEvent.VK_D, Direction.HORIZONTAL, 1);
		
		keyEventActions.add(keyEvent_Up);
		keyEventActions.add(keyEvent_Down);
		keyEventActions.add(keyEvent_Left);
		keyEventActions.add(keyEvent_Right);
	}
	
	private KeyEventAction createMoveKeyEventAction(int keyEvent, final Direction direction, int speed)
	{
		KeyEventAction keyEventAction = new KeyEventAction()
		{
			private final int charKeyEvent = keyEvent;
			
			@Override
			public int getCharKeyEvent()
			{
				return charKeyEvent;
			}
			
			@Override
			public void action(boolean isKeyPressed)
			{
				directionSpeeds[direction.getDirectionCode()] = isKeyPressed ? speed : 0;
			}
		};
		return keyEventAction;
	}
	
	public float[][] getVertex3fs()
	{
		float[][] vertexes = new float[3][3];
		
		vertexes[0] = new float[]
		{
			-(width / 2f) + position_x, -(height / 2f) + position_y, position_depth
		};
		
		vertexes[1] = new float[]
		{
			-(width / 2f) + position_x, (height / 2f) + position_y, position_depth
		};
		vertexes[2] = new float[]
		{
			(width / 2f) + position_x, (height / 2f) + position_y, position_depth
		};
		// vertexes[3] = new float[]
		// {
		// (width / 2f) + position_x, -(height / 2f) + position_y,
		// position_depth
		// };
		
		return vertexes;
	}
	
	private long lastTimestamp;
	
	public void calculatePosition()
	{
		long newTimestamp = System.nanoTime();
		if (lastTimestamp != 0)
		{
			double timeElapsed = newTimestamp - lastTimestamp;
			double factor = 0.000000001;
			
			double verticalDirection = directionSpeeds[Direction.VERTICAL.getDirectionCode()];
			double vertical = timeElapsed * factor * verticalDirection;
			
			double horizontalDirection = directionSpeeds[Direction.HORIZONTAL.getDirectionCode()];
			double horizontal = timeElapsed * factor * horizontalDirection;
			
			position_y += vertical;
			position_x += horizontal;
		}
		lastTimestamp = newTimestamp;
	}
	
	public void keyEventAction(KeyEvent keyEvent, boolean isKeyPressed)
	{
		keyEventActions.stream()
				.filter(keyEventAction -> keyEventAction.getCharKeyEvent() == keyEvent.getKeyCode())
				.parallel()
				.forEach(consumer -> consumer.action(isKeyPressed));
	}
}