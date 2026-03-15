<template>
  <div class="relative min-h-screen bg-[#fcfcfc] font-jakarta">
    <TheSidebar activePage="Accueil" />

    <main class="ml-64 p-12">
      <div class="mb-8 ml-4 flex items-center gap-2 text-[13px] text-[#5f527a]">
        <Home class="w-4 h-4 cursor-pointer hover:text-[#7c3aed]" @click="$router.push('/student')" />
        <ChevronRight class="w-3 h-3 opacity-40" />
        <span class="cursor-pointer hover:text-[#7c3aed]" @click="$router.push('/student')">Accueil</span>
        <ChevronRight class="w-3 h-3 opacity-40" />
        <span class="cursor-pointer hover:text-[#7c3aed]" @click="$router.push('/semestre/5')">Semestre 5</span>
        <ChevronRight class="w-3 h-3 opacity-40" />
        <span class="font-bold text-[#7c3aed]">{{ moduleData.title }}</span>
      </div>

      <div class="mb-10 ml-4">
        <h1 class="text-[32px] font-bold text-[#1c0845]">{{ moduleData.title }}</h1>
        <p class="text-[#5f527a] font-medium">Évaluation des enseignements • {{ moduleData.code }}</p>
      </div>

      <div class="bg-white p-8 rounded-[32px] border border-[#e4e0eb66] shadow-sm mb-10 max-w-4xl ml-4">
        <div class="flex justify-between items-end mb-4">
          <div>
            <h3 class="text-lg font-bold text-[#1c0845]">Progression - {{ moduleData.title }}</h3>
            <p class="text-[#5f527a] text-sm">{{ completedCount }} sur {{ moduleData.subjects.length }} évaluations complétées</p>
          </div>
          <span class="text-4xl font-black text-[#7c3aed]">{{ progressPercentage }}%</span>
        </div>
        <div class="w-full bg-[#f2f1f4] h-4 rounded-full overflow-hidden">
          <div class="bg-gradient-to-r from-[#4f46e5] to-[#7c3aed] h-full rounded-full transition-all duration-1000"
               :style="{ width: progressPercentage + '%' }"></div>
        </div>
      </div>

      <div class="space-y-4 max-w-4xl ml-4">
        <div v-for="sub in moduleData.subjects" :key="sub.id" 
             class="bg-white p-6 rounded-[24px] border border-[#e4e0eb66] shadow-sm flex items-center justify-between">
          <div class="flex items-center gap-6">
            <div class="w-14 h-14 bg-purple-50 rounded-2xl flex items-center justify-center text-[#7c3aed]">
              <BookOpen class="h-7 w-7" />
            </div>
            <div>
              <p class="text-[10px] font-black text-[#8b5cf6] uppercase tracking-wider">{{ sub.code }}</p>
              <h4 class="font-bold text-[#1c0845]">{{ sub.name }}</h4>
              <p class="text-xs text-[#5f527a]">{{ sub.teacher }} • Date limite : {{ sub.deadline }}</p>
            </div>
          </div>
          
          <div class="flex items-center gap-4">
            <div v-if="!sub.completed" 
                 :class="[ 'flex items-center gap-1.5 text-[11px] font-bold px-3 py-1 rounded-full', 
                           getDaysRemaining(sub.deadline) <= 3 ? 'text-red-600 bg-red-50' : 'text-emerald-600 bg-emerald-50' ]">
              <AlertCircle class="w-3 h-3" />
              <span>Il vous reste {{ getDaysRemaining(sub.deadline) }} jours</span>
            </div>

            <button v-if="!sub.completed" 
        @click="$router.push(`/evaluate/${sub.id}`)"
        class="bg-[#2f0d73] text-white px-6 py-3 rounded-xl font-bold text-sm hover:bg-[#7c3aed] transition-all">
  Évaluer →
</button>
            <div v-else class="flex items-center gap-2 text-emerald-600 font-bold bg-emerald-50 px-4 py-2 rounded-full border border-emerald-100 text-sm">
              <CheckCircle2 class="h-4 w-4" /> Complétée
            </div>
          </div>
        </div>
        
        <div class="bg-indigo-50/50 border border-indigo-100 p-4 rounded-2xl flex items-start gap-4 mt-8">
          <CheckCircle2 class="w-6 h-6 text-[#7c3aed] mt-1" />
          <div>
            <h5 class="text-sm font-bold text-[#1c0845]">Vos réponses sont anonymes</h5>
            <p class="text-xs text-[#5f527a]">Toutes vos évaluations sont enregistrées de manière totalement anonyme. Aucune donnée personnelle n'est associée à vos réponses. Participez en toute confiance !</p>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute } from 'vue-router';
import { Home, ChevronRight, BookOpen, CheckCircle2, AlertCircle } from "lucide-vue-next";
import TheSidebar from '../components/TheSidebar.vue';

const route = useRoute();
const moduleId = parseInt(route.params.id);

// Function to calculate days remaining
const getDaysRemaining = (dateStr) => {
  const deadline = new Date(dateStr);
  const today = new Date();
  const diffTime = deadline - today;
  const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
  return diffDays > 0 ? diffDays : 0;
};

const modulesDatabase = {
  1: { code: 'UE501', title: 'Fondamentaux', subjects: [
    { id: 1, code: 'MAT501', name: "Mathématiques Appliquées", teacher: "Dr. Martin", deadline: "2026-03-18", completed: false },
    { id: 2, code: 'AL501', name: "Algèbre Linéaire", teacher: "Mme. Lebron", deadline: "2026-03-20", completed: false },
    { id: 3, code: 'STA501', name: "Statistiques Avancées", teacher: "Mme. Dupont", deadline: "2026-03-10", completed: true }
  ]},
};

const moduleData = computed(() => modulesDatabase[moduleId] || { title: 'Module inconnu', subjects: [] });
const completedCount = computed(() => moduleData.value.subjects.filter(s => s.completed).length);
const progressPercentage = computed(() => Math.round((completedCount.value / (moduleData.value.subjects.length || 1)) * 100));
</script>

<style scoped>
.font-jakarta { font-family: 'Plus Jakarta Sans', sans-serif; }
</style>