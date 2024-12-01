<template>
  <router-view/>
</template>

<script setup lang="ts">

import router from "@/router";

router.push({path: "/login"})
</script>

<style scoped>

</style>