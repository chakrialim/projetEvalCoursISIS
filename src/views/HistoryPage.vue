<template>
  <div class="relative min-h-screen bg-[#fcfcfc]">
    <TheSidebar activePage="Historique" />

    <main class="ml-64 p-12">
      <div class="mb-8 ml-4">
        <h1 class="text-[32px] font-bold text-[#1c0845]">Bonjour, Étudiant 👋</h1>
        <p class="text-[#5f527a] text-sm font-medium">FIE 3 • Semestre 5</p>
      </div>

      <div class="ml-4 mb-10 flex items-center gap-2 text-sm text-[#5f527a]">
        <Home class="w-4 h-4 cursor-pointer hover:text-[#8b5cf6]" @click="$router.push('/student')" />
        <ChevronRight class="w-3 h-3 opacity-50" />
        <span>Accueil</span>
      </div>

      <h2 class="ml-4 text-xl font-bold text-[#1c0845] mb-8">Sélectionnez un semestre</h2>

      <div class="flex flex-col lg:flex-row gap-10">
        <div class="grid grid-cols-2 md:grid-cols-3 xl:grid-cols-5 gap-4 flex-grow">
          <SemesterCard 
            v-for="s in semesters" 
            :key="s.id"
            :number="s.id"
            :active="s.id === currentSemester"
            :progress="s.progress"
            :total="s.total"
            @select="handleSemesterClick(s.id)"
          />
        </div>

        <RewardsSidebar 
          levelName="Contributeur Bronze"
          nextLevel="Évaluateur Argent"
          :xpProgress="15"
          :creditsNeeded="25"
          :stats="userStats"
        />
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import TheSidebar from '../components/TheSidebar.vue';
import SemesterCard from '../components/SemesterCard.vue';
import RewardsSidebar from '../components/RewardsSidebar.vue';
import { Home, ChevronRight } from 'lucide-vue-next';

const router = useRouter();

// Configuration des semestres
const currentSemester = 5;
const semesters = Array.from({ length: 10 }, (_, i) => ({
  id: i + 1,
  progress: i + 1 === 5 ? 3 : 0,
  total: 9
}));

// Statistiques utilisateur
const userStats = [
  { label: 'Crédits', value: '0', color: 'text-[#a78bfa]' },
  { label: 'Évaluations', value: '0', color: 'text-[#a78bfa]' },
  { label: 'Par éval.', value: '+5', color: 'text-[#4f46e5]' }
];

const handleSemesterClick = (id) => {
  if (id === currentSemester) {
    router.push('/modules');
  }
};
</script>
