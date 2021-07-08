package org.example.knowboxTest.tmp;

import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.Lists;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Tmp {

    public static void main(String[] args) {

        Model modelAB = new Model("A", "B");
        Model modelCD = new Model("C", "D");
        List<Model> models = Lists.newArrayList(modelAB, modelCD);

        /*List<String> resultSet = models.stream()
                .flatMap(e -> Stream.of(e.getStringA(), e.getStringB()))
                .collect(Collectors.toList());*/

        List<Model> modelTmp = Lists.newArrayList();
        modelTmp = null;

    }

    @Test
    public void tmp() {
        Model model1 = new Model("u_name", "u1_email");
        Model model2 = new Model("u_name", null);
        Optional<Model> opt = Optional.of(model2);
        // assert opt.get().getStringB() == null ? false: true;
        // assert false;
        // assertTrue(opt.get().getStringA() != null);
        // opt.ifPresent( u -> assertEquals(model1.getStringA(), u.getStringA()));
        // System.out.println(opt.get().getStringB());
        Model model = new Model("aa", "bb");
        log.info("Using orElse");
        Model ret1 = Optional.ofNullable(model).orElse(createModel());
        log.info("Using orElseGet");
        Model ret2 = Optional.ofNullable(model).orElseGet(() -> createModel());
        System.out.println("done");
    }

    private Model createModel() {
        log.debug("create new Model");
        Model tmp = new Model("newA", "newB");
        return tmp;
    }

    @Test
    public void tmp2() {
        Clazz clazz = new Clazz("original");
        // Clazz clazz = null;

        createClazz1(clazz);
        createClazz2(clazz);
    }

    private Clazz createClazz1(Clazz clazz) {
        log.debug("createClazz1");
        return clazz != null ? clazz : new Clazz("createClazz1");
    }

    private Clazz createClazz2(IClazz clazz) {
        log.debug("createClazz2");
        return clazz != null ? (Clazz)clazz : new Clazz("createClazz2");
    }

}
