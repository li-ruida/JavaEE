package Tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * @author lrd
 * @date 2022-10-21 下午1:00
 */
public class resetTag extends TagSupport {
    @Override
    public int doEndTag() throws JspException {
        Bean.NumberGuessBean numberGuess=(Bean.NumberGuessBean) pageContext.getSession().getAttribute("numguess");
        numberGuess.reset();
        pageContext.getSession().setAttribute("numguess",numberGuess);
        return Tag.EVAL_BODY_INCLUDE;
    }


}
