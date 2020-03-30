package my.study.graduation.service;

import my.study.graduation.model.Menu;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.ToConverters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    private CrudMenuRepository repository;

    @Autowired
    public MenuService(CrudMenuRepository repository) {
        this.repository = repository;
    }

    public List<MenuTo> getForDay(LocalDate localDate) {
        List<Menu> dayMenus = repository.getByDay(localDate);
        return ToConverters.menuListIntoMenuToList(dayMenus);
    }

    @Cacheable("menuTo")
    public List<MenuTo> getForToday(){
        return getForDay(LocalDate.now());
    }

    @Transactional
    public Menu save(Menu menu){
        return repository.save(menu);
    }
}
