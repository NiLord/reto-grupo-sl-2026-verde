package com.appbg.appbg.domain.port.out;

import com.appbg.appbg.domain.model.Producto;
import java.util.*;

public interface ProductoRepository {
    public List<Producto> listarProductos();
}
