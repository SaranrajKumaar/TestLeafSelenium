package Week3.Day2.HomeAssignment.inheritance;

public class Elements {

 public static void main(String[] args) {

      // Button
        Button btn = new Button();
        btn.click();
        btn.submit();

        // TextField
        TextField tf = new TextField();
        tf.setText("Hello");
        System.out.println(tf.getText());

        // CheckBoxButton
        CheckBoxButton cb = new CheckBoxButton();
        cb.click();
        cb.submit();
        cb.clickCheckButton();

        // RadioButton
        RadioButton rb = new RadioButton();
        rb.click();
        rb.submit();
        rb.selectRadioButton();
}
}