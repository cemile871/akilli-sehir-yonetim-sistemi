import { useState } from 'react'
import Sidebar from './components/Sidebar'
import Topbar from './components/Topbar'
import GenelBakis from './pages/GenelBakis'
import Trafik from './pages/Trafik'
import Senaryo from './pages/Senaryo'
import Enerji from './pages/Enerji'
import Cevre from './pages/Cevre'
import AcilDurum from './pages/AcilDurum'
import Raporlar from './pages/Raporlar'
import './App.css'

const PAGES = {
  genel: GenelBakis,
  trafik: Trafik,
  senaryo: Senaryo,
  enerji: Enerji,
  cevre: Cevre,
  acil: AcilDurum,
  rapor: Raporlar,
}

export default function App() {
  const [activePage, setActivePage] = useState('genel')
  const [zaman, setZaman] = useState('Bugün')
  const [kavsak, setKavsak] = useState('all')

  const ActiveComponent = PAGES[activePage]

  return (
    <div className="app">
      <Sidebar activePage={activePage} setActivePage={setActivePage} />
      <div className="main">
        <Topbar
          zaman={zaman} setZaman={setZaman}
          kavsak={kavsak} setKavsak={setKavsak}
          activePage={activePage}
        />
        <div className="content">
          <ActiveComponent zaman={zaman} kavsak={kavsak} />
        </div>
      </div>
    </div>
  )
}
