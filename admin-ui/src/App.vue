<template>
  <router-view/>
</template>

<script setup lang="ts">
import {userInfo} from "@/api/notesnookUsers";
import router from "@/router";
import {onMounted} from "vue";


onMounted(() => {
  userInfo().then(() => {
    router.push({path: "/"})
  }).catch(() => {
    router.push({path: "/login"})
  })
})

</script>

<style>
html,body{
  margin: 0;
  padding: 0;
}
</style>