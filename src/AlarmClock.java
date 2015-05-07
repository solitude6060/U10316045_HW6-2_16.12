import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AlarmClock extends JPanel {
	private int hour;
	private int minute;
	private int second;
	
	public AlarmClock(){
		setCurrentTime();
	}
	
	public AlarmClock(int hour, int minute, int second){
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}
	
	/** Return hour */
	public int getHour() {
		return hour;
	}

	/** Set a new hour */
	public void setHour(int hour) {
		this.hour = hour;
		repaint();
	}

	/** Return minute */
	public int getMinute() {
		return minute;
	}

	/** Set a new minute */
	public void setMinute(int minute) {
		this.minute = minute;
		repaint();
	}

	/** Return second */
	public int getSecond() {
		return second;
	}

	/** Set a new second */
	public void setSecond(int second) {
		this.second = second;
		repaint();
	}	

	@Override /** Draw the clock */
	protected void paintComponent(Graphics g) {
		 super.paintComponent(g);

		// Initialize clock parameters
		int clockRadius = (int)(Math.min(getWidth(), getHeight()) * 0.8 * 0.5);
		int xCenter = getWidth() / 2;
		int yCenter = getHeight() / 2;

		// Draw circle
		g.setColor(Color.black);
		g.drawOval(xCenter - clockRadius, yCenter - clockRadius,2 * clockRadius, 2 * clockRadius);
		g.drawString("12", xCenter - 5, yCenter - clockRadius + 12);
		g.drawString("9", xCenter - clockRadius + 3, yCenter + 5);
		g.drawString("3", xCenter + clockRadius - 10, yCenter + 3);
		g.drawString("6", xCenter - 3, yCenter + clockRadius - 3);

		// Draw second hand
		int sLength = (int)(clockRadius * 0.8);
		int xSecond = (int)(xCenter + sLength * Math.sin(second * (2 * Math.PI / 60)));
		int ySecond = (int)(yCenter - sLength * Math.cos(second * (2 * Math.PI / 60)));
		g.setColor(Color.red);
		g.drawLine(xCenter, yCenter, xSecond, ySecond);

		// Draw minute hand
		int mLength = (int)(clockRadius * 0.65);
		int xMinute = (int)(xCenter + mLength * Math.sin(minute * (2 * Math.PI / 60)));
		int yMinute = (int)(yCenter - mLength * Math.cos(minute * (2 * Math.PI / 60)));
		g.setColor(Color.blue);
		g.drawLine(xCenter, yCenter, xMinute, yMinute);

		// Draw hour hand
		int hLength = (int)(clockRadius * 0.5);
		int xHour = (int)(xCenter + hLength *Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		int yHour = (int)(yCenter - hLength *  Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));
		g.setColor(Color.green);
		g.drawLine(xCenter, yCenter, xHour, yHour);
	
		//add number clock
		String str = getHour()+" : "+getMinute() + " : " +getSecond();
		g.setFont(new Font(str,Font.BOLD,20));
		//magenta
		g.setColor(Color.magenta);
		g.drawString(str, xCenter-(int)(clockRadius/2.5), yCenter+(int)(clockRadius*1.2));
	}
	
	
	public void setCurrentTime() {
		// Construct a calendar for the current date and time
		Calendar calendar = new GregorianCalendar();

		// Set current hour, minute and second
		this.hour = calendar.get(Calendar.HOUR_OF_DAY);
		this.minute = calendar.get(Calendar.MINUTE);
		this.second = calendar.get(Calendar.SECOND);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	//////
	public void RingTheBell(double Hour, double Minute, double Second){
		if((Hour == this.getHour()) && (Minute == this.getMinute()) && (Second == this.getSecond())){
			try{
			AudioInputStream audioIn =AudioSystem.getAudioInputStream(
			this.getClass().getResource("Perfect Day.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioIn);
			clip.start();					
			}
			catch(Exception ex){
			}
		}
	}
	
}
