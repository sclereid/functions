package complex;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;

public class Main extends JFrame implements MouseListener,
MouseMotionListener, MouseWheelListener, KeyListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String status_text = "default message";
	int width = 800, height = 600;
	int mws;
	
	double delta = 0.004;
	int unit = 180;
	double maxt = 180;
	int half_window_size = 300;
	
	complex z(double t)
	{
		return complex.exp(t).mul(new complex(t,0));
	}
	
	void showStatus(String str)
	{
		status_text = str;
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
	
	Main()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(new Color(17,18,125));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		this.addKeyListener(this);
		this.requestFocus();
		
		this.setBackground(Color.lightGray);
		this.setSize(40+2*half_window_size, 40+2*half_window_size);
		
		repaint_full_flag = true;
		mx = my = -10;
		mws = 10; //default circle size
		
		this.setVisible(true);
	}
	
	int resize(double r)
	{
		return (int) (r*unit+half_window_size+20);
	}
	
	Image iBuffer;
	Graphics gBuffer;
	private boolean repaint_full_flag;
	boolean dragging;
	
	void draw()
	{
		if(iBuffer==null)
	    {
	       iBuffer=createImage(this.getSize().width,this.getSize().height);
	       gBuffer=iBuffer.getGraphics();
	    }
	    gBuffer.setColor(getBackground());
	    gBuffer.fillRect(0,0,this.getSize().width,this.getSize().height);
	    
	    Graphics g = gBuffer;
	    
	    this.setSize(40+2*half_window_size, 40+2*half_window_size);
		g.setColor(Color.white);
		g.fillRect(20, 20, 2*half_window_size, 2*half_window_size);
		
	
		g.setColor(Color.lightGray);
		g.drawLine(20+half_window_size, 20, 20+half_window_size, 20+2*half_window_size);
		g.drawLine(20, 20+half_window_size, 20+2*half_window_size, 20+half_window_size);
		for(int r = unit; r < half_window_size; r+=unit)
		{
			g.drawLine(20+half_window_size + r, 18+half_window_size, 20+half_window_size + r, 22+half_window_size);
			g.drawLine(20+half_window_size - r, 18+half_window_size, 20+half_window_size - r, 22+half_window_size);
			g.drawLine(18+half_window_size, 20+half_window_size + r, 22+half_window_size, 20+half_window_size + r);
			g.drawLine(18+half_window_size, 20+half_window_size - r, 22+half_window_size, 20+half_window_size - r);
		}
		
		g.setColor(Color.yellow);
		
		complex prev = z(0), curr;
		for(double t = 0; t < maxt; t+=delta)
		{
			g.setColor(new Color((int)(35+10*Math.cos(t)),(int)(118+10*Math.cos(t)),(int)(28+10*Math.cos(t))));
			curr = z(t);
			//System.out.println(""+curr);
			//if((x>4)||(x<-4)||(y>4)||(y<-4)) continue;
			g.drawLine(resize(prev.get_real()), resize(-prev.get_imag()),
					resize(curr.get_real()), resize(-curr.get_imag()));
			prev = curr;
		}
	}
	
	int mx, my;
	
	@SuppressWarnings("unused")
	public void paint(Graphics g)
	{
		if(repaint_full_flag)
		{
			draw();
			repaint_full_flag = false;
		}
		g.drawImage(iBuffer, 0, 0, this);
		//paint from buffer
		
		g.setColor(Color.gray);
		if(dragging)
		{
			g.fillOval(mx-mws, my-mws, mws*2, mws*2);
			dragging = false;
		}
		else
			g.drawOval(mx-mws, my-mws, mws*2, mws*2);
		
		complex z1 = new complex(-1,1), z2 = new complex(0,Math.PI);
		this.showStatus(""+z1.toString2());
		
		//showing status text
		g.setFont(new Font("dialog", 10, 10));
		g.setXORMode(Color.cyan);
		g.drawString(status_text, 20, height);
		g.setPaintMode();
		//repaint();
	}
	
	public void update(Graphics g)
	{
		paint(g);
	}
	@Override
	public void mouseDragged(MouseEvent me) 
	{
		dragging = true;
		mx = me.getX();
		my = me.getY();
		//showStatus("Mouse at x:"+mx+"\t  y:"+my);
		repaint();
	}
	
	
	public void mouseMoved(MouseEvent me) 
	{
		mx = me.getX();
		my = me.getY();
		//showStatus("Mouse at x:"+mx+"\t  y:"+my);
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent me) 
	{
		
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent mwe) 
	{
		// TODO Auto-generated method stub
		mws+=-mwe.getWheelRotation();
		
	}
	
	final double mb = Math.sqrt(Math.sqrt(2));
	
	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		this.showStatus("Key pressed");
		int key = ke.getKeyCode();
		switch(key)
		{
		case KeyEvent.VK_EQUALS:
			//b*=0.99;
			break;
	
		case KeyEvent.VK_MINUS:
			//b*=1.01;
			break;
			
		case KeyEvent.VK_COMMA:
			if(unit>3)
				unit -= 2;
			break;
			
		case KeyEvent.VK_PERIOD:
			unit += 2;
			break;
			
		case KeyEvent.VK_SEMICOLON:
			delta *= mb;
			break;
			
		case KeyEvent.VK_QUOTE:
			//this.showStatus("\'");
			if(delta>0.001)
				delta /= mb;
			break;
		}
		draw();
		repaint();
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
