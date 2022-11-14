import { useQuery, gql, DocumentNode } from '@apollo/client'
import './conjugation.css'

interface InfoTableRowProps {
  label?: string
  value?: string
}

const InfoTableRow: (props: InfoTableRowProps) => JSX.Element =
  ({ label, value }) => {
    if (label == null || value == null) {
      return <></>
    } else {
      return (
        <tr>
          <th>{label} :</th>
          <td>{value}</td>
        </tr>
      )
    }
  }

const conjugationQuery: DocumentNode = gql`
    query Conjugate($verb: String!) {
        conjugate(verb: $verb) {
            participePresent
            participePasse
            auxiliaire
            formePronominale
            formeNonPronominale
            present
            imparfait
            passeCompose
            futurSimple
            subjonctifPresent
            conditionnelPresent
            imperatif
        }
    }`

interface ConjugationTableProps {
  title: string
  conjugation?: string[]
}

const pronoun = [
  "je/j'",
  'tu',
  'il',
  'nous',
  'vous',
  'ils',
]

const renderEmptyString: (str: string) => string =
  str => str === '' ? '—' : str

const ConjugationTable: (props: ConjugationTableProps) => JSX.Element =
  ({ title, conjugation }) => {
    if (conjugation == null) {
      return <></>
    }

    return (
      <div className='conjugation-table'>
        <h3>{title}</h3>

        <table>
          <tbody>
            {[...pronoun.keys()].map(i => (
              <tr>
                <th>{pronoun[i]}</th>
                <td>{renderEmptyString(conjugation[i])}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    )
  }

export interface ConjugationProps {
  verb: string
}

export const Conjugation: (props: ConjugationProps) => JSX.Element =
  ({ verb }) => {
    const { data, loading, error } = useQuery(conjugationQuery, { variables: { verb } })

    if (loading) {
      return <p>"Loading..."</p>
    }

    if (error != null) {
      return <pre>{error.message}</pre>
    }

    const conj = data.conjugate

    return (
      <>
        <h2>{verb}</h2>
        <table id='basic-info'>
          <tbody>
            <InfoTableRow label='Participe présent' value={conj.participePresent} />
            <InfoTableRow label='Participe passé' value={conj.participePasse} />
            <InfoTableRow label='Auxiliaire' value={conj.auxiliaire} />
            <InfoTableRow label='Forme pronominale' value={conj.formePronominale} />
            <InfoTableRow label='Forme non pronominale' value={conj.formeNonPronominale} />
          </tbody>
        </table>

        <div id='conjugation-tables'>
          <ConjugationTable title='Présent' conjugation={conj.present} />
          <ConjugationTable title='Imparfait' conjugation={conj.imparfait} />
          <ConjugationTable title='Passé composé' conjugation={conj.passeCompose} />
          <ConjugationTable title='Futur simple' conjugation={conj.futurSimple} />
          <ConjugationTable title='Subjonctif' conjugation={conj.subjonctifPresent} />
          <ConjugationTable title='Conditionnel' conjugation={conj.conditionnelPresent} />
          <ConjugationTable title='Impératif' conjugation={conj.imperatif} />
        </div>
      </>
    )
  }
