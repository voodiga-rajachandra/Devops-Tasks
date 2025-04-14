import styled from "styled-components";
import { ButtonLink } from "components/ui/ButtonLink";
import { DataType } from "types";

const Container = styled.div`
  max-width: 110px;
`;

const Author = styled.p`
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.small};
  letter-spacing: 0.08em;
  line-height: 1.2;
  text-transform: uppercase;
`;

const Date = styled.p`
  color: ${({ theme }) => theme.color.white};
  font-size: ${({ theme }) => theme.fontSize.small};
  letter-spacing: 0.08em;
  margin: ${({ theme }) => `${theme.spacings.m} 0`};
  text-align: right;
  text-transform: uppercase;
`;

type DetailsProps = {
  data: DataType;
  className?: string;
};

export function Details({ data, className }: DetailsProps) {
  return (
    <Container className={className}>
      <Author>{data.author}</Author>
      <Date>{data.date}</Date>
      <ButtonLink {...data.button} />
    </Container>
  );
}
