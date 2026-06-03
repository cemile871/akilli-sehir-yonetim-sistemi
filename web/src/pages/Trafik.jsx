import { useState } from 'react'
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer } from 'recharts'

const beklemeSureleri = [
  { kavsak: 'Çarşı', normal: 42, yogun: 85 },
  { kavsak: 'Tofaş', normal: 22, yogun: 45 },
  { kavsak: 'İzzet Paşa', normal: 38, yogun: 75 },
  { kavsak: 'Palu Yolu', normal: 18, yogun: 35 },
  { kavsak: 'Üniversite', normal: 28, yogun: 55 },
]

export default function Trafik() {
  const [params, setParams] = useState({
    yogunluk: 70, bekleme: 80, acil: 100,
    yogunSaat: 140, minYesil: 15, maxYesil: 90
  })

  const update = (key, val) => setParams(p => ({ ...p, [key]: Number(val) }))

  return (
    <div>
      <div className="grid2">
        <div className="card">
          <div className="card-title">Algoritma parametreleri</div>
          <div className="card-sub">Kaydırarak optimizasyon ağırlıklarını ayarla</div>

          {[
            { key: 'yogunluk', label: 'Yoğunluk ağırlığı', min: 0, max: 100, fmt: v => v },
            { key: 'bekleme', label: 'Bekleme süresi ağırlığı', min: 0, max: 100, fmt: v => v },
            { key: 'acil', label: 'Acil araç önceliği', min: 0, max: 100, fmt: v => v },
            { key: 'yogunSaat', label: 'Yoğun saat faktörü', min: 100, max: 200, fmt: v => `${(v/100).toFixed(1)}x` },
            { key: 'minYesil', label: 'Min. yeşil süre (sn)', min: 10, max: 30, fmt: v => v },
            { key: 'maxYesil', label: 'Max. yeşil süre (sn)', min: 60, max: 120, fmt: v => v },
          ].map(p => (
            <div key={p.key} className="param-row">
              <div className="param-top">
                <span className="param-label">{p.label}</span>
                <span className="param-val">{p.fmt(params[p.key])}</span>
              </div>
              <input
                type="range" min={p.min} max={p.max}
                value={params[p.key]}
                onChange={e => update(p.key, e.target.value)}
              />
            </div>
          ))}

          <button
            className="btn btn-primary"
            style={{ width: '100%', justifyContent: 'center', padding: '10px', marginTop: '8px' }}
            onClick={() => alert('Parametreler uygulandı!')}
          >
            ✓ Parametreleri Uygula
          </button>
        </div>

        <div className="card">
          <div className="card-title">Kavşak bekleme süreleri karşılaştırması</div>
          <div className="card-sub">Normal vs Yoğun saat (saniye)</div>
          <ResponsiveContainer width="100%" height={320}>
            <BarChart data={beklemeSureleri} barCategoryGap="30%">
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="kavsak" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} label={{ value: 'saniye', angle: -90, position: 'insideLeft', fontSize: 11 }} />
              <Tooltip />
              <Legend wrapperStyle={{ fontSize: 12 }} />
              <Bar dataKey="normal" fill="#378ADD" radius={[4,4,0,0]} name="Normal" />
              <Bar dataKey="yogun" fill="#E24B4A" radius={[4,4,0,0]} name="Yoğun saat" />
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>
    </div>
  )
}
