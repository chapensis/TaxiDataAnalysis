/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const taxiRouter = {
  path: '/road',
  component: Layout,
  redirect: '/road/complex-road',
  name: 'Road',
  meta: {
    title: 'Road',
    icon: 'guide'
  },
  children: [
    {
      path: 'road-overview',
      component: () => import('@/views/road/overview'),
      name: 'RoadOverview',
      // 这里是真正对图标起作用的地方
      meta: { title: 'RoadOverview' }
    },
    {
      path: 'road-info',
      component: () => import('@/views/road/index'),
      name: 'RoadInfo',
      // 这里是真正对图标起作用的地方
      meta: { title: 'Road' }
    }
  ]
}
export default taxiRouter
