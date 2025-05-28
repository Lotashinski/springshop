function addProduct(identifier) {
    const productsStorage = localStorage.getItem("products") ?? "{}"
    const products = JSON.parse(productsStorage)
    let currentCount = products[identifier] ?? 0
    products[identifier] = ++currentCount
    localStorage.setItem("products", JSON.stringify(products))
}