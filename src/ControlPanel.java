/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.lang.reflect.Method;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class ControlPanel extends JPanel {
	private final MainFrame frame;
	private final JLabel classNameLabel = new JLabel("Class name");
	private final JTextField classNameField = new JTextField(30);
	private final JLabel textLabel = new JLabel("Text");
	private final JTextField textField = new JTextField(10);
	private final JButton createButton = new JButton("Add component");

	public ControlPanel(MainFrame frame)
	{
		this.frame = frame;
		init();
	}
	private void init()
	{
		add(classNameLabel); add(classNameField); add(textLabel); add(textField); add(createButton);
		createButton.addActionListener(e ->
		{
			try
			{
				JComponent comp = createDynamicComponent(classNameField.getText());
				setComponentText(comp, textField.getText());
				frame.designPanel.addAtRandomLocation(comp);
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		});
	}
	private JComponent createDynamicComponent(String className) {
		className = "javax.swing." + className;
		Class object;
		Object instance = null;

		try
		{
			object = Class.forName(className);
			instance = object.newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return (JComponent)instance;
	}
	private void setComponentText(JComponent comp, String text) throws Exception
	{
		Class[] signature = new Class[] {String.class};

		Method method = comp.getClass().getMethod("setText", signature);

		method.invoke(comp, text);
	}
}