import {createStore} from 'vuex'
import router from "@/router";

export default createStore({
  state: {
    hasRouters: false,
    editableTabsValue: '/index',
    editableTabs: [
      {
        title: '首页',
        name: '/index'
      }
    ]
  },
  getters: {
    GET_TOKEN:state =>{
      return sessionStorage.getItem("token");
    },
    GET_MENU_LIST:state =>{
      return JSON.parse(sessionStorage.getItem("menuList"));
    },
    GET_USER_INFO:state =>{
      return JSON.parse(sessionStorage.getItem("userInfo"));
    }
  },
  mutations: {
    SET_TOKEN:(state, token)=>{
      sessionStorage.setItem("token", token);
    },
    SET_MENU_LIST: (state, menuList) => {
      sessionStorage.setItem("menuList", JSON.stringify(menuList))
    },
    SET_USER_INFO: (state, userInfo) => {
      sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
    },
    SET_ROUTES_STATE: (state, hasRoutes) => {
      state.hasRouters = hasRoutes
    },
    ADD_TABS: (state, tab) => {
      if (state.editableTabs.findIndex(e => e.name === tab.path) === -1) {
        state.editableTabs.push({
          title: tab.name,
          name: tab.path
        })
      }
      state.editableTabsValue = tab.path
    },
    RESET_TABS: (state) => {
      state.editableTabsValue = '/index';
      state.editableTabs = [
        {
          title: '首页',
          name: '/index'
        }
      ]
    }

  },
  actions: {
    logout(){
      window.sessionStorage.clear();
      router.replace("/login")
    }

  },
  modules: {
  }
})
