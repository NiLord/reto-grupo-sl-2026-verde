def maquina_expendedora():
    # Precio del producto
    precio = 75
    dinero_insertado = 0
    monedas_aceptadas = [5, 10, 25] # Centavos, por ejemplo [3]

    print(f"Producto seleccionado: $0.{precio}")
    print("Monedas aceptadas: 5, 10, 25 centavos")

    # Bucle para recibir dinero
    while dinero_insertado < precio:
        try:
            moneda = int(input(f"Inserta moneda (Faltan {precio - dinero_insertado}): "))
            if moneda in monedas_aceptadas:
                dinero_insertado += moneda
                print(f"Total ingresado: {dinero_insertado}")
            else:
                print("Moneda no válida.")
        except ValueError:
            print("Entrada inválida.")

    # Cálculo del cambio
    cambio = dinero_insertado - precio
    print(f"Compra exitosa. Tu cambio es: {cambio} centavos.")

# Ejecutar la simulación
maquina_expendedora()
