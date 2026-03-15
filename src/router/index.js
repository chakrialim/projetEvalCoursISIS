import { createRouter, createWebHistory } from 'vue-router';

// Importing views
import HomePage from '../views/HomePage.vue';
import LoginPage from '../views/LoginPage.vue';
import StudentPage from '../views/StudentPage.vue';
import ProfilePage from '../views/ProfilePage.vue';
import HistoryPage from '../views/HistoryPage.vue';
import ModulesPages from '../views/ModulesPages.vue';

const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage },
  {
    path: '/student',
    name: 'Dashboard',
    component: StudentPage,
    meta: { requiresAuth: true }
  },
  {
    path: '/profile',
    name: 'Profile',
    component: ProfilePage,
    meta: { requiresAuth: true }
  },
  {
    path: '/history',
    name: 'History',
    component: HistoryPage,
    meta: { requiresAuth: true }
  },
  // Semesters & Modules
  {
    path: '/semestre/:id',
    name: 'Modules',
    component: ModulesPages,
    meta: { requiresAuth: true }
  },
  {
    path: '/module/:id',
    name: 'ModuleEvaluation',
    component: () => import('../views/ModuleEvaluationPage.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/evaluate/:subjectId',
    name: 'Evaluate',
    component: () => import('../views/EvaluationFormPage.vue'),
    meta: { requiresAuth: true }
  },
  // --- New Routes for Results & Stats ---
  {
    path: '/stats',
    name: 'StatsList',
    component: () => import('../views/StatsSemesterList.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/stats/semester/:id',
    name: 'StatsSubjectList',
    component: () => import('../views/StatsSubjectList.vue'),
    meta: { requiresAuth: true }
  },
  {
    path: '/stats/detail/:subjectId',
    name: 'SubjectStatsDetail',
    // Ensure the case matches exactly with your file system:
    component: () => import('../views/SubjectStatsDetail.vue'),
    meta: { requiresAuth: true }
  },
  // Catch-all
  { path: '/:pathMatch(.*)*', redirect: '/' },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior() {
    return { top: 0 };
  }
});

router.beforeEach((to, from, next) => {
  const isAuthenticated = true; // Replace with real auth logic
  if (to.meta.requiresAuth && !isAuthenticated) {
    next('/login');
  } else {
    next();
  }
});

export default router;