import styled from "styled-components";

const StyledButtonLink = styled.a`
  background-color: ${({ theme }) => theme.color.white};
  border-radius: ${({ theme }) => theme.borderRadius.button};
  color: ${({ theme }) => theme.color.black};
  display: block;
  font-size: ${({ theme }) => theme.fontSize.small};
  font-weight: ${({ theme }) => theme.fontWeight.bold};
  padding: ${({ theme }) => `${theme.spacings.s} ${theme.spacings.m}`};
  text-decoration: none;
  text-transform: uppercase;
`;

type ButtonLinkProps = {
  label: string;
  href: string;
  target: string;
};

export function ButtonLink({ label, href, target }: ButtonLinkProps) {
  return (
    <StyledButtonLink href={href} target={target}>
      {label}
    </StyledButtonLink>
  );
}
