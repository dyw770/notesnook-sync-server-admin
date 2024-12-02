import {createRouter, createWebHashHistory} from 'vue-router'


const routerData = [
    {path: '/login', component: () => import('../views/LoginView.vue')},
    {path: '/404', component: () => import('../views/404.vue')},
    {
        path: '/',
        name: 'index',
        component: () => import('../views/HomeView.vue')
    }
]

const router = createRouter({
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes: routerData
})

export {router as default, routerData}
