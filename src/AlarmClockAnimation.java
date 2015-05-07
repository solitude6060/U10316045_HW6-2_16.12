import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class AlarmClockAnimation extends JFrame {
	private AlarmClock clock = new AlarmClock();
	
	private JTextField JtfHour = new JTextField();
	private JTextField JtfMinute = new JTextField();
	private JTextField JtfSecond = new JTextField();
	
	double Hour,Minute,Second;
	
	public AlarmClockAnimation() {
					
		JPanel p2 = new JPanel(new GridLayout(1,7));
		JButton setAlarm = new JButton("Set");
	
		p2.add(new JLabel("Hour"));
		p2.add(JtfHour);
		
		p2.add(new JLabel("Minute"));
		p2.add(JtfMinute);
		
		p2.add(new JLabel("Second"));
		p2.add(JtfSecond);
		
	
		p2.add(setAlarm);
		setAlarm.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				Hour = Double.parseDouble(JtfHour.getText());
				Minute = Double.parseDouble(JtfMinute.getText());
				Second = Double.parseDouble(JtfSecond.getText());
						
			}
		}
		);
		
		add(clock,BorderLayout.CENTER);
		add(p2, BorderLayout.SOUTH);
		
		// Create a timer with delay 1000 ms
		Timer timer = new Timer(1000, new TimerListener());
		timer.start();	
	}

	private class TimerListener implements ActionListener {
  
		@Override /** Handle the action event */
		public void actionPerformed(ActionEvent e) {
			// Set new time and repaint the clock to display current time
			clock.setCurrentTime();
			clock.repaint();
			clock.RingTheBell(Hour,Minute,Second);
		 
		}
	}

	/** Main method */
	public static void main(String[] args) {
		JFrame frame = new AlarmClockAnimation();
		frame.setTitle("AlarmClockAnimation");
		frame.setSize(420, 350);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	
}
