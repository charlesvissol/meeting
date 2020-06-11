package org.angrybee.meet.examples;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jdatepicker.JDatePicker;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;

public class JDatePickerExample {

	public static void main(String[] args) {
	     try {
	    	 UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
	      } catch (ClassNotFoundException ex) {
	          ex.printStackTrace();
	      } catch (InstantiationException ex) {
	    	  ex.printStackTrace();
	      } catch (IllegalAccessException ex) {
	    	  ex.printStackTrace();
	      } catch (UnsupportedLookAndFeelException ex) {
	    	  ex.printStackTrace();
	      }
    	

    	
    	//Create and set up the window.
        JFrame frame = new JFrame("Date Picker Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
       //JDatePicker datePicker = new JDatePicker();
       
       //datePicker.setBackground(Color.darkGray);
       
       //DatePicker date = new DatePicker();
       //date.setBackground(Color.darkGray);
       
       
       //FlatDatePickerUI datePicker2 = new FlatDatePickerUI();
       
       URL dateImageURL = FullDemo.class.getResource("/images/datepickerbutton1.png");
       Image dateExampleImage = Toolkit.getDefaultToolkit().getImage(dateImageURL);
       ImageIcon dateExampleIcon = new ImageIcon(dateExampleImage);
       // Create the date picker, and apply the image icon.
       Locale locale = Locale.getDefault();
       locale = new Locale("fr");
       DatePickerSettings dateSettings = new DatePickerSettings(locale);
       //Display week number
       dateSettings.setWeekNumbersDisplayed(true, true);
       //Set textfield background
       dateSettings.setColor(DateArea.TextFieldBackgroundValidDate, Color.DARK_GRAY);
       // Set backgrounds:
       dateSettings.setColor(DateArea.CalendarBackgroundNormalDates, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundOverallCalendarPanel, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundMonthAndYearMenuLabels, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundTodayLabel, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundClearLabel, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundMonthAndYearNavigationButtons, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.BackgroundTopLeftLabelAboveWeekNumbers, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.CalendarBackgroundSelectedDate, Color.DARK_GRAY);
       dateSettings.setColor(DateArea.CalendarBorderSelectedDate, Color.BLUE);
       dateSettings.setColorBackgroundWeekdayLabels(Color.DARK_GRAY, true);
       //set format
       dateSettings.setFormatForDatesCommonEra("dd/MM/yyyy");
       
       
       
       DatePicker datePicker = new DatePicker(dateSettings);
       datePicker.setBackground(Color.darkGray);
       datePicker.setDateToToday();
       JButton datePickerButton = datePicker.getComponentToggleCalendarButton();
       datePickerButton.setText("");
       datePickerButton.setIcon(dateExampleIcon);
       
      
       
       JPanel p = new JPanel();
       frame.add(p);
       
       
       
       p.add(datePicker);
       //p.add(date);
       
       //p.add(datePicker2); 
        
        //frame.getRootPane().add(datePicker);


        //Display the window.
        frame.pack();
        frame.setVisible(true);

	}

}
