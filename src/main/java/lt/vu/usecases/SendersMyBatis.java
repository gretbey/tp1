package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.SenderMapper;
import lt.vu.mybatis.dao.TeamMapper;
import lt.vu.mybatis.model.Sender;
import lt.vu.mybatis.model.Team;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class SendersMyBatis {
    @Inject
    private SenderMapper senderMapper;

    @Getter
    private List<Sender> allSenders;

    @Getter @Setter
    private Sender senderToCreate = new Sender();

    @PostConstruct
    public void init() {
        this.loadAllSenders();
    }

    private void loadAllSenders() {
        this.allSenders = senderMapper.selectAll();
    }

    @Transactional
    public String createSender() {
        senderMapper.insert(senderToCreate);
        return "/myBatis/senders?faces-redirect=true\"";
    }
}
