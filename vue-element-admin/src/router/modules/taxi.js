/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/views/layout/Layout'

const taxiRouter = {
  path: '/taxi',
  component: Layout,
  redirect: '/taxi/complex-taxi',
  name: 'Taxi',
  meta: {
    title: 'Taxi',
    icon: 'dashboard'
  },
  children: [
    {
      path: 'taxi-overview',
      component: () => import('@/views/taxi/overview'),
      name: 'TaxiOverview',
      // 这里是真正对图标起作用的地方
      meta: { title: 'TaxiOverview' }
    },
    {
      path: 'taxi-info',
      component: () => import('@/views/taxi/index'),
      name: 'TaxiInfo',
      // 这里是真正对图标起作用的地方
      meta: { title: 'Taxi' }
    }
  ]
}
export default taxiRouter
