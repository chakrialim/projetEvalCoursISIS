<template>
  <div class="relative min-h-screen bg-[#fcfcfc] font-jakarta">
    <TheSidebar activePage="Accueil" />

    <main class="ml-64 p-12">
      <div class="mb-6 ml-4">
        <h1 class="text-[28px] font-bold text-[#1c0845]">Bonjour, Étudiant 👋</h1>
        <p class="text-[#5f527a] text-xs font-semibold tracking-widest uppercase opacity-70">FIE 3 • Semestre {{ selectedSemester }}</p>
      </div>

      <div class="ml-4 mb-8 flex items-center gap-2 text-[13px] text-[#5f527a]">
        <Home class="w-4 h-4 cursor-pointer hover:text-[#7c3aed] transition-colors" @click="$router.push('/student')" />
        <ChevronRight class="w-3 h-3 opacity-40" />
        <span class="cursor-pointer hover:text-[#7c3aed]" @click="$router.push('/student')">Accueil</span>
        <ChevronRight class="w-3 h-3 opacity-40" />
        <span class="font-bold text-[#7c3aed]">Semestre {{ selectedSemester }}</span>
      </div>

      <h2 class="ml-4 text-lg font-bold text-[#1c0845] mb-6">Unités d'enseignement</h2>

      <div class="max-w-4xl ml-4 space-y-4">
        <div v-for="ue in filteredModules" :key="ue.id" 
             @click="goToModule(ue.id)"
             class="bg-white p-5 rounded-[24px] border border-[#e4e0eb66] shadow-sm hover:shadow-md transition-all group cursor-pointer relative overflow-hidden flex flex-col justify-center min-h-[110px]">
          
          <div class="flex items-center justify-between">
            <div class="flex items-center gap-5 flex-grow">
              <div class="w-12 h-12 rounded-xl flex items-center justify-center text-white shadow-md bg-gradient-to-br from-[#4f46e5] via-[#7c3aed] to-[#d946ef]">
                <component :is="ue.icon" class="w-6 h-6" />
              </div>

              <div class="flex-grow">
                <p class="text-[10px] font-black text-[#8b5cf6] uppercase tracking-[0.15em] mb-0.5">{{ ue.code }}</p>
                <h3 class="text-lg font-bold text-[#1c0845] group-hover:text-[#7c3aed] transition-colors">{{ ue.title }}</h3>
                <p class="text-xs text-[#5f527a]/60 font-medium">{{ ue.description }}</p>
              </div>
            </div>
            
            <div class="flex flex-col items-end gap-2 min-w-[120px]">
               <div class="flex items-center gap-2">
                 <span class="text-[11px] font-bold text-[#1c0845]">{{ Math.round((ue.validated / ue.total) * 100) }}%</span>
                 <ChevronRight class="w-4 h-4 text-[#1c0845]/20 group-hover:text-[#7c3aed] group-hover:translate-x-1 transition-all" />
               </div>
            </div>
          </div>

          <div class="mt-4">
            <div class="flex justify-between text-[9px] font-black uppercase tracking-widest text-[#5f527a]/50 mb-1.5">
              <span>{{ ue.validated }} / {{ ue.total }} matières</span>
            </div>
            <div class="w-full bg-[#f3f4f6] h-1.5 rounded-full overflow-hidden">
              <div :style="{ width: (ue.validated / ue.total) * 100 + '%' }"
                   class="h-full rounded-full bg-gradient-to-r from-[#4f46e5] via-[#7c3aed] to-[#d946ef] transition-all duration-1000 shadow-[0_0_8px_rgba(124,58,237,0.3)]">
              </div>
            </div>
          </div>
        </div>
      </div>
    </main>
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import TheSidebar from '../components/TheSidebar.vue';
import { Home, ChevronRight, BookText, Terminal, Code, Users2, Brain, BarChart3 } from 'lucide-vue-next';

const route = useRoute();
const router = useRouter(); // Initialize router for navigation
const selectedSemester = computed(() => parseInt(route.params.id) || 5);

// Navigation logic
const goToModule = (id) => {
  router.push({ name: 'ModuleEvaluation', params: { id: id } });
};

const allModules = [
  { id: 1, semester: 5, code: 'UE501', title: 'Fondamentaux', description: 'Mathématiques et sciences de base', icon: BookText, validated: 1, total: 2 },
  { id: 2, semester: 5, code: 'UE502', title: 'Informatique', description: 'Développement logiciel et systèmes', icon: Terminal, validated: 1, total: 3 },
  { id: 3, semester: 5, code: 'UE503', title: 'Langues & Communication', description: 'Anglais technique et soft skills', icon: Brain, validated: 1, total: 2 },
  { id: 4, semester: 5, code: 'UE504', title: 'Management & Projet', description: 'Gestion de projet et méthodologies', icon: BarChart3, validated: 0, total: 2 },
  { id: 5, semester: 6, code: 'UE601', title: 'Génie Logiciel', description: 'Architecture et design patterns', icon: Code, validated: 0, total: 4 },
  { id: 6, semester: 6, code: 'UE602', title: 'Réseaux Avancés', description: 'Protocoles et sécurité', icon: Users2, validated: 2, total: 3 },
  { id: 7, semester: 6, code: 'UE603', title: 'Intelligence Artificielle', description: 'Machine Learning et Data Science', icon: Brain, validated: 0, total: 3 },
  { id: 8, semester: 6, code: 'UE604', title: 'Stage Technique', description: 'Immersion en milieu professionnel', icon: BarChart3, validated: 0, total: 1 },
];

const filteredModules = computed(() => {
  return allModules.filter(m => m.semester === selectedSemester.value);
});
</script>

<style scoped>
.font-jakarta { font-family: 'Plus Jakarta Sans', sans-serif; }
.group { animation: slideUp 0.4s ease-out forwards; }
@keyframes slideUp {
  from { opacity: 0; transform: translateY(15px); }
  to { opacity: 1; transform: translateY(0); }
}
</style>
