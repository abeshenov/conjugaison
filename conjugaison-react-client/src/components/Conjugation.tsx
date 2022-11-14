import { useQuery, gql, DocumentNode } from '@apollo/client'
import './conjugation.css'

interface InfoTableRowProps {
  label: string | null
  value: string | null
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
        }
    }`

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
          <table className='basic-info'>
            <tbody>
              <InfoTableRow label='Participe présent' value={conj.participePresent} />
              <InfoTableRow label='Participe passé' value={conj.participePasse} />
              <InfoTableRow label='Auxiliaire' value={conj.auxiliaire} />
              <InfoTableRow label='Forme pronominale' value={conj.formePronominale} />
              <InfoTableRow label='Forme non pronominale' value={conj.formeNonPronominale} />
            </tbody>
          </table>
        </>
      )
    }
