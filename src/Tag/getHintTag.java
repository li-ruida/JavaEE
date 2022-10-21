package Tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author lrd
 * @date 2022-10-21 下午2:03
 */
public class getHintTag extends TagSupport {
    @Override
    public int doStartTag() throws JspException {
        JspWriter out= pageContext.getOut();
        Bean.NumberGuessBean numberGuess=(Bean.NumberGuessBean) pageContext.getSession().getAttribute("numguess");
        String str= String.valueOf(numberGuess.getHint());
        try {
            out.print(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Tag.EVAL_BODY_INCLUDE;
    }
}
