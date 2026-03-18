<template>
  <div class="min-h-screen bg-[#fcfcfc] p-12 font-jakarta">
    <div class="max-w-2xl mx-auto">
      
      <button @click="$router.back()" class="text-sm text-[#5f527a] mb-6 flex items-center gap-1 hover:text-[#7c3aed] transition-colors">
        ← Retour au tableau de bord
      </button>

      <div class="bg-white p-6 rounded-[24px] border border-[#e4e0eb66] mb-8 shadow-sm relative overflow-hidden">
        <div class="absolute top-0 right-0 w-24 h-full bg-[#7c3aed]/5 -skew-x-12"></div>
        <h1 class="text-xl font-bold text-[#1c0845]">{{ subjectName }}</h1>
        <p class="text-sm text-[#5f527a] font-medium opacity-70">{{ moduleName }} • {{ teacherName }}</p>
      </div>

      <StepProgress 
        :current="currentQuestionIndex" 
        :total="totalQuestions" 
        :percentage="progressPercentage" 
      />

      <transition name="slide" mode="out-in">
        <QuestionCard 
          :key="currentQuestionIndex"
          :text="currentQuestionText"
          :options="options"
          v-model="answers[currentQuestionIndex]"
        />
      </transition>

      <div class="flex justify-between items-center mt-8">
        <button 
          :disabled="currentQuestionIndex === 0" 
          @click="prevStep" 
          class="px-6 py-3 font-bold text-[#5f527a] disabled:opacity-20 hover:text-[#1c0845] transition-colors"
        >
          Précédent
        </button>
        
        <button 
          @click="nextStep" 
          :disabled="answers[currentQuestionIndex] === null"
          class="bg-[#2f0d73] text-white px-10 py-3 rounded-xl font-bold flex items-center gap-2 hover:bg-[#7c3aed] disabled:bg-gray-200 disabled:text-gray-400 disabled:cursor-not-allowed transition-all shadow-lg shadow-purple-900/10"
        >
          {{ isLastQuestion ? 'Envoyer l\'évaluation' : 'Suivant' }}
          <span v-if="!isLastQuestion">→</span>
        </button>
      </div>

    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useRouter } from 'vue-router';
// Import des composants enfants
import StepProgress from '../components/StepProgress.vue';
import QuestionCard from '../components/QuestionCard.vue';

const router = useRouter();

// Données contextuelles (pourront être récupérées via Props ou API plus tard)
const subjectName = "Mathématiques Appliquées";
const moduleName = "Fondamentaux";
const teacherName = "Dr. Martin";

// Liste des questions du questionnaire ISIS
const questions = [
  "L'enseignant a communiqué les règles de fonctionnement du cours dès le début.",
  "Le contenu du cours correspond aux objectifs pédagogiques annoncés.",
  "Les supports de cours mis à disposition sont clairs et pertinents.",
  "La charge de travail demandée est adaptée au niveau du module.",
  "Globalement, je suis satisfait de la qualité de cet enseignement."
];

// Options de réponses basées sur l'échelle de Likert
const options = [
  { id: 1, label: "Tout à fait d'accord", value: 4, stars: 4 },
  { id: 2, label: "Plutôt d'accord", value: 3, stars: 3 },
  { id: 3, label: "Plutôt pas d'accord", value: 2, stars: 2 },
  { id: 4, label: "Pas du tout d'accord", value: 1, stars: 1 },
  { id: 5, label: "Ne se prononce pas", value: 0, stars: -1 },
];

// État réactif
const currentQuestionIndex = ref(0);
const answers = ref(new Array(questions.length).fill(null));

// Propriétés calculées pour l'UI
const totalQuestions = questions.length;
const currentQuestionText = computed(() => questions[currentQuestionIndex.value]);
const progressPercentage = computed(() => ((currentQuestionIndex.value + 1) / totalQuestions) * 100);
const isLastQuestion = computed(() => currentQuestionIndex.value === totalQuestions - 1);

// Logique de navigation
const nextStep = () => {
  if (isLastQuestion.value) {
    submitEvaluation();
  } else {
    currentQuestionIndex.value++;
  }
};

const prevStep = () => {
  if (currentQuestionIndex.value > 0) {
    currentQuestionIndex.value--;
  }
};

// Simulation de l'envoi des données
const submitEvaluation = () => {
  const finalPayload = {
    subject: subjectName,
    responses: answers.value,
    submittedAt: new Date().toISOString()
  };
  
  console.log("ISIS Eval - Envoi des données :", finalPayload);
  
  // Redirection vers l'accueil après succès
  router.push('/student'); 
};
</script>

<style scoped>
.font-jakarta { font-family: 'Plus Jakarta Sans', sans-serif; }

/* Animation fluide lors du changement de question */
.slide-enter-active, .slide-leave-active {
  transition: all 0.3s ease;
}
.slide-enter-from { opacity: 0; transform: translateX(30px); }
.slide-leave-to { opacity: 0; transform: translateX(-30px); }
</style>