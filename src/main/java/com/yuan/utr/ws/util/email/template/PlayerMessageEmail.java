package com.yuan.utr.ws.util.email.template;

import com.yuan.utr.model.persistent.Player;
import com.yuan.utr.ws.util.email.MailUtil;

/**
 * Created by V644593 on 8/16/2016.
 */
public class PlayerMessageEmail extends SiteEmail
{
    public PlayerMessageEmail(String emailAddr, String toName, Player fromPlayer, String content)
    {
        this.setToAddr(emailAddr);
        this.setSubject(toName + ", New Message from UTR League ");
        String actualContent = emailTemplate.replace("[name]", toName)
                .replace("[fromName]", fromPlayer.getFirstName() + " " + fromPlayer.getLastName())
                .replace("[content]", content);
        this.setHtmlContent(actualContent);
    }

    private static String emailTemplate = "Hi [name], <br><br>" +
            "You got a new message from [fromName] at UTR League <br><br>" +
            "Here is your message. <br><br>" +
            "<pre> [content] </pre> <br><br>" +
            "Please log onto <a href=\"http://www.utrleague.com/utr/message.html\" target=\"_blank\">urtleague.com</a> to reply to the " +
            "message. Do not reply this email directly since it won't work.";

}
