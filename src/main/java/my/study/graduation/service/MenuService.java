package my.study.graduation.service;

import my.study.graduation.model.Menu;
import my.study.graduation.repository.CrudDishRepository;
import my.study.graduation.repository.CrudMenuRepository;
import my.study.graduation.to.MenuTo;
import my.study.graduation.util.ToConverters;
import my.study.graduation.util.exceptions.NotFoundInDataBaseException;
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
    private final RestaurantService restaurantService;
    private final CrudDishRepository dishRepository;

    @Autowired
    public MenuService(CrudMenuRepository repository, RestaurantService restaurantService, CrudDishRepository dishRepository) {
        this.repository = repository;
        this.restaurantService = restaurantService;
        this.dishRepository = dishRepository;
    }

    public List<MenuTo> getForDay(LocalDate localDate) {
        List<Menu> dayMenus = repository.getByDay(localDate);
        return ToConverters.menuListIntoMenuToList(dayMenus);
    }

    @Cacheable("menuTo")
    public List<MenuTo> getForToday() {
        return getForDay(LocalDate.now());
    }

    @Transactional
    public Menu save(Menu menu) {
        return repository.save(menu);
    }

    @Transactional
    public Menu create(MenuTo menuTo) {
        Menu menu = ToConverters.menuToIntoMenu(menuTo);
        menu.setRestaurant(restaurantService.get(menuTo.getRestaurant_id()));
        return repository.save(menu);
    }

    public Menu get(int id) {
        return repository.getById(id).orElseThrow(() -> new NotFoundInDataBaseException(Menu.class, id));
    }

    @Transactional
    public void updateDishes(MenuTo menuTo) {
        Menu menu = get(menuTo.getId());
        dishRepository.deleteByMenu_Id(menuTo.getId());
        menu.setDishes(ToConverters.dishesFromTo(menuTo.getDishes()));
        repository.save(menu);
    }
}
