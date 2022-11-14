import { Conjugation } from './components/Conjugation'

const App: () => JSX.Element =
  () => (
    <div className='App'>
      <Conjugation verb='avoir' />
    </div>
  )

export default App
