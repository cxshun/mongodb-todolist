package custom;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenxiaoshun on 14-7-1.
 */
public class DateTag extends TagSupport {

    private String value;

    private String pattern;

    public void setValue(String value) {
        this.value = value;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public int doStartTag() throws JspException {
        long time = Long.valueOf(value);
        Date date = new Date(time);

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateValue = sdf.format(date);

        try {
            pageContext.getOut().write(dateValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }
}
