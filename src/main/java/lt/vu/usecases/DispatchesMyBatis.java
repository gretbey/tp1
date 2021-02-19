package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.DispatchMapper;
import lt.vu.mybatis.dao.TeamMapper;
import lt.vu.mybatis.model.Dispatch;
import lt.vu.mybatis.model.Team;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DispatchesMyBatis {
    @Inject
    private DispatchMapper dispatchMapper;

    @Getter
    private List<Dispatch> allDispatches;

    @Getter @Setter
    private Dispatch dispatchToCreate = new Dispatch();

    @PostConstruct
    public void init() {
        this.loadAllDispatches();
    }

    private void loadAllDispatches() {
        this.allDispatches = dispatchMapper.selectAll();
    }

    @Transactional
    public String createDispatch() {
        dispatchMapper.insert(dispatchToCreate);
        return "index";
    }
}