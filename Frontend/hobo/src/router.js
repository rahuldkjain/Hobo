import Vue from 'vue'
import Router from 'vue-router'
import Category from './views/Category.vue'
import AddProduct from './views/AddProduct.vue'
import Cart from './views/Cart.vue'
import Checkout from './views/Checkout.vue'
import Home from './views/Home.vue'
import Login from './views/Login.vue'
import ManageProduct from './views/ManageProduct.vue'
import Product from './views/Product.vue'
import Profile from './views/Profile.vue'
import Search from './views/Search.vue'
import SuccessPage from './views/SuccessPage.vue'
import ViewProduct from './views/ViewProduct.vue'

Vue.use(Router)

export default new Router({
  mode: 'history',
  routes: [{
    name: 'Home',
    path: '/',
    component: Home

  },
  {
    name: 'AddProductnt',
    path: '/addproduct',
    component: AddProduct
  },
  {
    name: 'Cart',
    path: '/cart',
    component: Cart
  },
  {
    name: 'Checkout',
    path: '/checkout',
    component: Checkout
  },
  {
    name: 'Category',
    path: '/category',
    component: Category
  },
  {
    name: 'Login',
    path: '/login',
    component: Login
  },
  {
    name: 'ManageProduct',
    path: '/manageprod',
    component: ManageProduct
  },
  {
    name: 'Product',
    path: '/product',
    component: Product
  },
  {
    name: 'Profile',
    path: '/profile',
    component: Profile
  },
  {
    name: 'Search',
    path: '/search',
    component: Search
  },
  {
    name: 'SuccessPage',
    path: '/success',
    component: SuccessPage
  },
  {
    name: 'ViewProduct',
    path: '/viewproduct',
    component: ViewProduct
  },
]
  
})
