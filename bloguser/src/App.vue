<template>
    <div id="app">
        <router-view v-if="isRouterAlive"/>
    </div>
</template>
<script>
export default {
    data() {
        return {
            isRouterAlive: true

        }
    },
    methods: {
        reload() {
            this.isRouterAlive = false
            this.$nextTick(() => (this.isRouterAlive = true))
        }
    },
    mounted() {
        this.reload()
    }

}
</script>

<style lang="scss">

</style>
