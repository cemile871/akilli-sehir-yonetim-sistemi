import { useState } from 'react'
import { BarChart, Bar, XAxis, YAxis, CartesianGrid, Tooltip, ResponsiveContainer, Cell } from 'recharts'

const SENARYOLAR = {
  normal: { ad: 'Normal trafik', bekleme: '28 saniye', kapasite: '1.100 araç/saat', mod: 'Standart optimizasyon', tepki: '✓ Normal', iy: 'Baz değer', cls: 'badge-g' },
  yogun: { ad: 'Yoğun saat', bekleme: '72 saniye', kapasite: '950 araç/saat', mod: 'Rush hour modu (+%40)', tepki: '⚠ Aktif', iy: '-%40 bekleme süresi', cls: 'badge-w' },
  kaza: { ad: 'Kaza', bekleme: '95 saniye', kapasite: '600 araç/saat', mod: 'Kaza modu — yönlendirme aktif', tepki: '⚠ Alarm! Rota önerildi', iy: 'Yönlendirme aktif', cls: 'badge-r' },
  yol: { ad: 'Yol çalışması', bekleme: '80 saniye', kapasite: '700 araç/saat', mod: 'Yol çalışması modu', tepki: '⚠ 1 şerit kapalı', iy: 'Alternatif güzergah', cls: 'badge-r' },
  acil: { ad: 'Acil araç', bekleme: '0 sn (güzergah açık)', kapasite: 'Normal', mod: 'KRİTİK — Yeşil dalga', tepki: '✓ Güzergah açıldı', iy: '-%56 müdahale süresi', cls: 'badge-b' },
  yagmur: { ad: 'Kötü hava', bekleme: '38 saniye', kapasite: '850 araç/saat', mod: 'Güvenli mod -%20', tepki: 'Kaza riski -%35', iy: 'Güvenlik öncelikli', cls: 'badge-w' },
}

const karsilastirmaData = [
  { name: 'Normal', bekleme: 28, color: '#639922' },
  { name: 'Yoğun', bekleme: 72, color: '#EF9F27' },
  { name: 'Kaza', bekleme: 95, color: '#E24B4A' },
  { name: 'Yol Çalış.', bekleme: 80, color: '#E24B4A' },
  { name: 'Acil Araç', bekleme: 0, color: '#378ADD' },
  { name: 'Kötü Hava', bekleme: 38, color: '#EF9F27' },
]

const TABLO = [
  { senaryo: 'Normal', bekleme: '28 sn', kapasite: '1.100/saat', iy: 'Baz', durum: 'badge-g', durumText: 'İyi' },
  { senaryo: 'Yoğun Saat', bekleme: '72 sn', kapasite: '950/saat', iy: '-%40', durum: 'badge-w', durumText: 'Orta' },
  { senaryo: 'Kaza', bekleme: '95 sn', kapasite: '600/saat', iy: 'Yönlendirme', durum: 'badge-r', durumText: 'Kritik' },
  { senaryo: 'Yol Çalışması', bekleme: '80 sn', kapasite: '700/saat', iy: 'Alternatif', durum: 'badge-r', durumText: 'Kritik' },
  { senaryo: 'Acil Araç', bekleme: '0 sn', kapasite: 'Normal', iy: '-%56', durum: 'badge-b', durumText: 'Aktif' },
  { senaryo: 'Kötü Hava', bekleme: '38 sn', kapasite: '850/saat', iy: 'Kaza -%35', durum: 'badge-w', durumText: 'Orta' },
]

export default function Senaryo() {
  const [aktif, setAktif] = useState('normal')
  const s = SENARYOLAR[aktif]

  return (
    <div>
      <div className="alert alert-g">
        <span>ℹ️</span>
        <span>Senaryoyu seçin, algoritmanın tepkisini görün ve tüm senaryoları karşılaştırın.</span>
      </div>

      <div className="grid2">
        <div className="card">
          <div className="card-title">Senaryo seç</div>
          <div className="card-sub">6 farklı trafik senaryosu — gerçek algoritma tepkileri</div>
          <div className="sen-grid">
            {[
              { id: 'normal', icon: '✅', label: 'Normal trafik', cls: '' },
              { id: 'yogun', icon: '⏰', label: 'Yoğun saat', cls: 'warning' },
              { id: 'kaza', icon: '⚠️', label: 'Kaza', cls: 'danger' },
              { id: 'yol', icon: '🔧', label: 'Yol çalışması', cls: 'danger' },
              { id: 'acil', icon: '🚑', label: 'Acil araç', cls: 'danger' },
              { id: 'yagmur', icon: '🌧️', label: 'Kötü hava', cls: 'warning' },
            ].map(btn => (
              <button
                key={btn.id}
                className={`sen-btn ${btn.cls} ${aktif === btn.id ? 'active' : ''}`}
                onClick={() => setAktif(btn.id)}
              >
                <div style={{ fontSize: 20, marginBottom: 4 }}>{btn.icon}</div>
                {btn.label}
              </button>
            ))}
          </div>

          <div className="sonuc-box">
            {[
              { label: 'Aktif senaryo', val: <span className={`badge ${s.cls}`}>{s.ad}</span> },
              { label: 'Bekleme süresi', val: s.bekleme },
              { label: 'Geçiş kapasitesi', val: s.kapasite },
              { label: 'Algoritma modu', val: s.mod },
              { label: 'Sistem tepkisi', val: s.tepki },
              { label: 'İyileşme oranı', val: s.iy },
            ].map((row, i) => (
              <div key={i} className="sonuc-row">
                <span className="sonuc-label">{row.label}</span>
                <span className="sonuc-val">{row.val}</span>
              </div>
            ))}
          </div>
        </div>

        <div className="card">
          <div className="card-title">Senaryo karşılaştırması</div>
          <div className="card-sub">Tüm senaryolar için bekleme süresi (saniye)</div>
          <ResponsiveContainer width="100%" height={300}>
            <BarChart data={karsilastirmaData}>
              <CartesianGrid strokeDasharray="3 3" stroke="#f1f5f9" />
              <XAxis dataKey="name" tick={{ fontSize: 11 }} />
              <YAxis tick={{ fontSize: 11 }} label={{ value: 'saniye', angle: -90, position: 'insideLeft', fontSize: 11 }} />
              <Tooltip />
              <Bar dataKey="bekleme" radius={[4,4,0,0]} name="Bekleme (sn)">
                {karsilastirmaData.map((entry, index) => (
                  <Cell key={index} fill={entry.color} />
                ))}
              </Bar>
            </BarChart>
          </ResponsiveContainer>
        </div>
      </div>

      <div className="card">
        <div className="card-title">Tüm senaryoların detaylı karşılaştırması</div>
        <div className="card-sub">Algoritmanın her senaryodaki performansı</div>
        <table className="tablo">
          <thead>
            <tr>
              <th>Senaryo</th>
              <th>Bekleme Süresi</th>
              <th>Kapasite</th>
              <th>İyileşme</th>
              <th>Durum</th>
            </tr>
          </thead>
          <tbody>
            {TABLO.map((row, i) => (
              <tr key={i}>
                <td>{row.senaryo}</td>
                <td>{row.bekleme}</td>
                <td>{row.kapasite}</td>
                <td>{row.iy}</td>
                <td><span className={`badge ${row.durum}`}>{row.durumText}</span></td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
